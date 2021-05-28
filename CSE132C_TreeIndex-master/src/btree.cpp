/**
 * @author See Contributors.txt for code contributors and overview of BadgerDB.
 *
 * @section LICENSE
 * Copyright (c) 2012 Database Group, Computer Sciences Department, University of Wisconsin-Madison.
 */

#include "btree.h"
#include "filescan.h"
#include "file.h"
#include "exceptions/bad_index_info_exception.h"
#include "exceptions/bad_opcodes_exception.h"
#include "exceptions/bad_scanrange_exception.h"
#include "exceptions/no_such_key_found_exception.h"
#include "exceptions/scan_not_initialized_exception.h"
#include "exceptions/index_scan_completed_exception.h"
#include "exceptions/file_not_found_exception.h"
#include "exceptions/file_exists_exception.h"
#include "exceptions/end_of_file_exception.h"
#include <string>
#include <unordered_map>

//#define DEBUG

namespace badgerdb
{

// -----------------------------------------------------------------------------
// BTreeIndex::BTreeIndex -- Constructor
// -----------------------------------------------------------------------------

BTreeIndex::BTreeIndex(const std::string & relationName,
					   std::string & outIndexName,
					   BufMgr *bufMgrIn,
					   const int attrByteOffset,
					   const Datatype attrType) {
	// check if index file exists
	std::ostringstream idxStr;
	idxStr << relationName << '.' << attrByteOffset;
	std::string indexName = idxStr.str(); 
	File* newFile;
	try {
		newFile = new BlobFile(indexName, true);
	} catch(FileExistsException& e) {
		newFile = new BlobFile(indexName, false);
	}

	// assign class members
	this->file = newFile;
	this->attributeType = attrType;
	this->attrByteOffset = attrByteOffset;
	this->bufMgr = bufMgrIn;

	// init root node
	PageId rootPageId;
	Page* rootPage;
	bufMgr->allocPage(this->file, rootPageId, rootPage);
	this->rootPageNum = rootPageId;

	if(this->attributeType == INTEGER) {
		this->leafOccupancy = INTARRAYLEAFSIZE;
		this->nodeOccupancy = INTARRAYNONLEAFSIZE;
		struct NonLeafNodeInt root = {.level = 1};
		struct NonLeafNodeInt* temp = (NonLeafNodeInt*)rootPage;
		*temp = root;
		this->bufMgr->unPinPage(this->file, rootPageId, true);
	} else if(this->attributeType == DOUBLE) { 
		this->leafOccupancy = DOUBLEARRAYLEAFSIZE;
		this->nodeOccupancy = DOUBLEARRAYNONLEAFSIZE;
		struct NonLeafNodeDouble root = {.level = 1};
		struct NonLeafNodeDouble* temp = (NonLeafNodeDouble*)rootPage;
		*temp = root;
		this->bufMgr->unPinPage(this->file, rootPageId, true);
	} else {
		this->leafOccupancy = STRINGARRAYLEAFSIZE;
		this->nodeOccupancy = STRINGARRAYNONLEAFSIZE;
		struct NonLeafNodeString root = {.level = 1};
		struct NonLeafNodeString* temp = (NonLeafNodeString*)rootPage;
		*temp = root;
		this->bufMgr->unPinPage(this->file, rootPageId, true);
	}

	// build index meta info
	const char* tempName = relationName.c_str();
	struct IndexMetaInfo metaInfo = {.attrByteOffset = attrByteOffset, .attrType = attrType, .rootPageNo = rootPageId};
	strcpy(metaInfo.relationName, tempName);
	
	// write meta info to index file
	PageId metaPageId;
	Page* headerPage;
	bufMgr->allocPage(newFile, metaPageId, headerPage);
	struct IndexMetaInfo* temp = (IndexMetaInfo*)headerPage;
	*temp = metaInfo;
	this->headerPageNum = metaPageId;
	this->bufMgr->unPinPage(newFile, metaPageId, true);

	// scan relation and insert one by one
	FileScan fileScan = FileScan(relationName, bufMgrIn);
	while(true) {
		try {
			RecordId record_id;
			fileScan.scanNext(record_id);
			std::string record = fileScan.getRecord();
	
			// pick key according to datatype and find field in record to insert
			switch(attrType) {
				case INTEGER:
				{	
					std::string key = record.substr(attrByteOffset, 4);
					int key_ptr;
					memcpy(&key_ptr, key.c_str(), 4);
					insertEntry(&key_ptr, record_id); 
					break;
				}
				case DOUBLE:
				{	
					std::string key = record.substr(attrByteOffset, 8);
					double key_ptr;
					memcpy(&key_ptr, key.c_str(), 8);
					insertEntry(&key_ptr, record_id); 
					break;
				}
				case STRING:
				{
					std::string key = record.substr(attrByteOffset, 10);
					insertEntry(key.c_str(), record_id);
					break;
				}
			}
		} catch(EndOfFileException& e) {
			break;
		}
	}
	this->scanExecuting = false;
	// store outindex name
	outIndexName = indexName;
}


// -----------------------------------------------------------------------------
// BTreeIndex::~BTreeIndex -- destructor
// -----------------------------------------------------------------------------

BTreeIndex::~BTreeIndex() {
	this->bufMgr->cleanUpPinnedPage(this->file);
	bufMgr->flushFile(this->file);
	delete this->file;
}
// -----------------------------------------------------------------------------
// BTreeIndex::findPageNoInNonLeaf -- find page number in non leaf
// -----------------------------------------------------------------------------

const PageId BTreeIndex::findPageNoInNonLeaf(Page* node, const void* key) {
	// find a place to insert in the non leaf
	if(this->attributeType == INTEGER) {
		struct NonLeafNodeInt* temp = (NonLeafNodeInt*)(node);
		
		int i;
		for(i = 0; i < temp->keyArrLength; i ++) {
			if(*(int*)key < temp->keyArray[i]) {
				return temp->pageNoArray[i];
			}

			if(*(int*)key == temp->keyArray[i]) {
				return temp->pageNoArray[i + 1];
			}
		}

		return temp->pageNoArray[i];
		
	} else if(this->attributeType == DOUBLE) {
		struct NonLeafNodeDouble* temp = (NonLeafNodeDouble*)(node);

		int i;
		for(i = 0; i < temp->keyArrLength; i ++) {
			if(*(double*)key < temp->keyArray[i]) {
				return temp->pageNoArray[i];
			}

			if(*(double*)key == temp->keyArray[i]) {
				return temp->pageNoArray[i + 1];
			}
		}

		return temp->pageNoArray[i];
	} else {
		struct NonLeafNodeString* temp = (NonLeafNodeString*)(node);
		char strKey[10];
		strncpy(strKey, (char*)key, 10);

		int i;
		for(i = 0; i < temp->keyArrLength; i ++) {
			if(strncmp(strKey, temp->keyArray[i], 10) < 0) {
				return temp->pageNoArray[i];
			}

			if(strncmp(strKey, temp->keyArray[i], 10) == 0) {
				return temp->pageNoArray[i + 1];
			}
		}

		return temp->pageNoArray[i];
	}
}

// -----------------------------------------------------------------------------
// BTreeIndex::insertKeyToLeaf -- insert key to leaf
// -----------------------------------------------------------------------------

void insertStringKeyToLeaf(LeafNodeString* leaf, const void* key, const RecordId rid) {
	// find place to insert
	for(int i = 0; i < leaf->keyArrLength; i ++) {
		if(strncmp(leaf->keyArray[i],(char*)key, 10) > 0) {
			char temp[10] = {'\0'};
			strncpy(temp, leaf->keyArray[i], 10);
			strncpy(leaf->keyArray[i], (char*)key, 10);

			// shift key to right
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				char curr[10] = {'\0'};
				strncpy(curr, leaf->keyArray[j], 10);
				strncpy(leaf->keyArray[j], temp, 10);
				strncpy(temp, curr, 10);
			}
			strncpy(leaf->keyArray[leaf->keyArrLength], temp, 10);
			
			// insert rid and shift to right
			RecordId temp_rid = leaf->ridArray[i];
			leaf->ridArray[i] = rid;
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				RecordId curr = leaf->ridArray[j];
				leaf->ridArray[j] = temp_rid;
				temp_rid = curr;
			}
			leaf->ridArray[leaf->keyArrLength] = temp_rid;

			leaf->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	strncpy(leaf->keyArray[leaf->keyArrLength], (char*)key, 10);
	leaf->ridArray[leaf->keyArrLength] = rid;
	leaf->keyArrLength ++;
}

void insertDoubleKeyToLeaf(LeafNodeDouble* leaf, const void* key, const RecordId rid) {
	// find place to insert
	for(int i = 0; i < leaf->keyArrLength; i ++) {
		if(leaf->keyArray[i] > *(double*)key) {
			double temp = leaf->keyArray[i];
			leaf->keyArray[i] = *(double*)key;

			// shift key to right
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				double curr = leaf->keyArray[j];
				leaf->keyArray[j] = temp;
				temp = curr;
			}
			leaf->keyArray[leaf->keyArrLength] = temp;
			
			// insert rid and shift to right
			RecordId temp_rid = leaf->ridArray[i];
			leaf->ridArray[i] = rid;
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				RecordId curr = leaf->ridArray[j];
				leaf->ridArray[j] = temp_rid;
				temp_rid = curr;
			}
			leaf->ridArray[leaf->keyArrLength] = temp_rid;

			leaf->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	leaf->keyArray[leaf->keyArrLength] = *(double*)key;
	leaf->ridArray[leaf->keyArrLength] = rid;
	leaf->keyArrLength ++;
}

void insertIntKeyToLeaf(LeafNodeInt* leaf, const void* key, const RecordId rid) {
	// find place to insert
	for(int i = 0; i < leaf->keyArrLength; i ++) {
		if(leaf->keyArray[i] > *(int*)key) {
			int temp = leaf->keyArray[i];
			leaf->keyArray[i] = *(int*)key;

			// shift key to right
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				int curr = leaf->keyArray[j];
				leaf->keyArray[j] = temp;
				temp = curr;
			}
			leaf->keyArray[leaf->keyArrLength] = temp;
			
			// insert rid and shift to right
			RecordId temp_rid = leaf->ridArray[i];
			leaf->ridArray[i] = rid;
			for(int j = i + 1; j < leaf->keyArrLength; j ++) {
				RecordId curr = leaf->ridArray[j];
				leaf->ridArray[j] = temp_rid;
				temp_rid = curr;
			}
			leaf->ridArray[leaf->keyArrLength] = temp_rid;

			leaf->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	leaf->keyArray[leaf->keyArrLength] = *(int*)key;
	leaf->ridArray[leaf->keyArrLength] = rid;
	leaf->keyArrLength ++;
}

// -----------------------------------------------------------------------------
// BTreeIndex:: insertKeyToNonLeaf -- insert key to non leaf
// -----------------------------------------------------------------------------

void insertIntKeyToNonLeaf(NonLeafNodeInt* node, const void* key, const PageId rightPage) {
	// find place to insert
	for(int i = 0; i < node->keyArrLength; i ++) {
		if(node->keyArray[i] > *(int*)key) {
			int temp = node->keyArray[i];
			node->keyArray[i] = *(int*)key;

			// shift key to right
			for(int j = i + 1; j < node->keyArrLength; j ++) {
				int curr = node->keyArray[j];
				node->keyArray[j] = temp;
				temp = curr;
			}
			node->keyArray[node->keyArrLength] = temp;
			
			// insert pageId and shift to right
			PageId temp_pageId = node->pageNoArray[i + 1];
			node->pageNoArray[i + 1] = rightPage;
			for(int j = i + 2; j < node->keyArrLength + 1; j ++) {
				PageId curr = node->pageNoArray[j];
				node->pageNoArray[j] = temp_pageId;
				temp_pageId = curr;
			}
			node->pageNoArray[node->keyArrLength + 1] = temp_pageId;

			node->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	node->keyArray[node->keyArrLength] = *(int*)key;
	node->pageNoArray[node->keyArrLength + 1] = rightPage;
	node->keyArrLength ++;
}

void insertDoubleKeyToNonLeaf(NonLeafNodeDouble* node, const void* key, const PageId rightPage) {
	// find place to insert
	for(int i = 0; i < node->keyArrLength; i ++) {
		if(node->keyArray[i] > *(double*)key) {
			double temp = node->keyArray[i];
			node->keyArray[i] = *(double*)key;

			// shift key to right
			for(int j = i + 1; j < node->keyArrLength; j ++) {
				double curr = node->keyArray[j];
				node->keyArray[j] = temp;
				temp = curr;
			}
			node->keyArray[node->keyArrLength] = temp;
			
			// insert pageId and shift to right
			PageId temp_pageId = node->pageNoArray[i + 1];
			node->pageNoArray[i + 1] = rightPage;
			for(int j = i + 2; j < node->keyArrLength + 1; j ++) {
				PageId curr = node->pageNoArray[j];
				node->pageNoArray[j] = temp_pageId;
				temp_pageId = curr;
			}
			node->pageNoArray[node->keyArrLength + 1] = temp_pageId;

			node->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	node->keyArray[node->keyArrLength] = *(double*)key;
	node->pageNoArray[node->keyArrLength + 1] = rightPage;
	node->keyArrLength ++;
}

void insertStringKeyToNonLeaf(NonLeafNodeString* node, const void* key, const PageId rightPage) {
	// find place to insert
	for(int i = 0; i < node->keyArrLength; i ++) {
		if(strncmp(node->keyArray[i], (char*)key, 10) > 0) {
			char temp[10];
			strncpy(temp, node->keyArray[i], 10);
			strncpy(node->keyArray[i], (char*)key, 10);

			// shift key to right
			for(int j = i + 1; j < node->keyArrLength; j ++) {
				char curr[10]; 
				strncpy(curr, node->keyArray[j],10);
				strncpy(node->keyArray[j], temp, 10);
				strncpy(temp, curr, 10);
			}
			strncpy(node->keyArray[node->keyArrLength], temp, 10);
			
			// insert pageId and shift to right
			PageId temp_pageId = node->pageNoArray[i + 1];
			node->pageNoArray[i + 1] = rightPage;
			for(int j = i + 2; j < node->keyArrLength + 1; j ++) {
				PageId curr = node->pageNoArray[j];
				node->pageNoArray[j] = temp_pageId;
				temp_pageId = curr;
			}
			node->pageNoArray[node->keyArrLength + 1] = temp_pageId;

			node->keyArrLength ++;
			return;
		}
	}

	// insert at last place
	strncpy(node->keyArray[node->keyArrLength], (char*)key, 10);
	node->pageNoArray[node->keyArrLength + 1] = rightPage;
	node->keyArrLength ++;
}

// -----------------------------------------------------------------------------
// BTreeIndex::splitLeafNode -- split leaf node
// -----------------------------------------------------------------------------

const std::pair<PageId, int*> BTreeIndex::splitLeafNodeInt(struct LeafNodeInt* node, int* key, const RecordId rid) {
	// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct LeafNodeInt* newNode = (LeafNodeInt*) newNodePage;
	memset(newNode->keyArray, 0, sizeof(newNode->keyArray));
	memset(newNode->ridArray, 0, sizeof(newNode->ridArray));
	int leftCnt = (this->leafOccupancy + 1) / 2;

	// map key to pageId
	std::unordered_map<int, RecordId> map;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		map.insert({node->keyArray[i], node->ridArray[i]});
	}
	map.insert({*key, rid});

	int tempKey[this->leafOccupancy + 1];
	bool inserted = false;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		if(*key < node->keyArray[i] && !inserted) {
			tempKey[i] = *key;
			inserted = true;
			continue;
		}

		if(!inserted) {
			tempKey[i] = node->keyArray[i];
		} else {
			tempKey[i] = node->keyArray[i - 1];
		}
	}
	if(!inserted) {
		tempKey[this->leafOccupancy] = *(int*)key;
	} else {
		tempKey[this->leafOccupancy] = node->keyArray[this->leafOccupancy - 1];
	}
	
	// modify left/right node
	for(int i = 0; i < this->leafOccupancy - leftCnt + 1; i ++) {
		newNode->keyArray[i] = tempKey[i + leftCnt];
		newNode->ridArray[i] = map[tempKey[i + leftCnt]];
	}

	for(int i = 0; i < leftCnt; i ++) {
		node->keyArray[i] = tempKey[i];
		node->ridArray[i] = map[tempKey[i]];
	}
	
	// clear left node
	memset(node->keyArray + leftCnt, 0, (this->leafOccupancy - leftCnt) * sizeof(int));
	memset(node->ridArray + leftCnt, 0, (this->leafOccupancy - leftCnt) * sizeof(struct RecordId));
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->leafOccupancy - leftCnt + 1;

	// set sibling ptr
	newNode->rightSibPageNo = node->rightSibPageNo;
	node->rightSibPageNo = newNodePageId;
	int* ret_key = &newNode->keyArray[0];

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, int*>(newNodePageId, ret_key);
}

const std::pair<PageId, double*> BTreeIndex::splitLeafNodeDouble(struct LeafNodeDouble* node, double* key, const RecordId rid) {
	// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct LeafNodeDouble* newNode = (LeafNodeDouble*) newNodePage;
	memset(newNode->keyArray, 0, sizeof(newNode->keyArray));
	memset(newNode->ridArray, 0, sizeof(newNode->ridArray));
	int leftCnt = (this->leafOccupancy + 1) / 2;

	// map key to pageId
	std::unordered_map<double, RecordId> map;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		map.insert({node->keyArray[i], node->ridArray[i]});
	}
	map.insert({*key, rid});

	double tempKey[this->leafOccupancy + 1];
	bool inserted = false;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		if(*key < node->keyArray[i] && !inserted) {
			tempKey[i] = *key;
			inserted = true;
			continue;
		}

		if(!inserted) {
			tempKey[i] = node->keyArray[i];
		} else {
			tempKey[i] = node->keyArray[i - 1];
		}
	}
	if(!inserted) {
		tempKey[this->leafOccupancy] = *(double*)key;
	} else {
		tempKey[this->leafOccupancy] = node->keyArray[this->leafOccupancy - 1];
	}
	
	// modify left/right node
	for(int i = 0; i < this->leafOccupancy - leftCnt + 1; i ++) {
		newNode->keyArray[i] = tempKey[i + leftCnt];
		newNode->ridArray[i] = map[tempKey[i + leftCnt]];
	}

	for(int i = 0; i < leftCnt; i ++) {
		node->keyArray[i] = tempKey[i];
		node->ridArray[i] = map[tempKey[i]];
	}
	
	// clear left node
	memset(node->keyArray + leftCnt, 0, (this->leafOccupancy - leftCnt) * sizeof(double));
	memset(node->ridArray + leftCnt, 0, (this->leafOccupancy - leftCnt) * sizeof(struct RecordId));
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->leafOccupancy - leftCnt + 1;

	// set sibling ptr
	newNode->rightSibPageNo = node->rightSibPageNo;
	node->rightSibPageNo = newNodePageId;
	double* ret_key = &newNode->keyArray[0];

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, double*>(newNodePageId, ret_key);
}

const std::pair<PageId, char*> BTreeIndex::splitLeafNodeString(struct LeafNodeString* node, char* key, const RecordId rid) {
	// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct LeafNodeString* newNode = (LeafNodeString*) newNodePage;
	memset(newNode->ridArray, 0, sizeof(newNode->ridArray));
	int leftCnt = (this->leafOccupancy + 1) / 2;

	std::unordered_map<std::string, RecordId> map;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		char tempChar[11] = {'\0'};
		strncpy(tempChar, node->keyArray[i], 10);
		map.insert({std::string(tempChar), node->ridArray[i]});
	}
	char tempChar[11] = {'\0'};
	strncpy(tempChar, key, 10);
	map.insert({std::string(tempChar), rid});

	char tempKey[this->leafOccupancy + 1][10];
	bool inserted = false;
	for(int i = 0; i < this->leafOccupancy; i ++) {
		if(strncmp(key, node->keyArray[i], 10) < 0 && !inserted) {
			strncpy(tempKey[i], key, 10);
			inserted = true;
			continue;
		}

		if(!inserted) {
			strncpy(tempKey[i], node->keyArray[i], 10);
		} else {
			strncpy(tempKey[i], node->keyArray[i - 1], 10);
		}
	}
	if(!inserted) {
		strncpy(tempKey[this->leafOccupancy], (char*)key, 10);
	} else {
		strncpy(tempKey[this->leafOccupancy], node->keyArray[this->leafOccupancy - 1], 10);
	}
	
	// modify left/right node
	for(int i = 0; i < this->leafOccupancy - leftCnt + 1; i ++) {
		strncpy(newNode->keyArray[i], tempKey[i + leftCnt], 10);
		char tempChar[11] = {'\0'};
		strncpy(tempChar, tempKey[i + leftCnt], 10);
		newNode->ridArray[i] = map[std::string(tempChar)];
	}

	for(int i = 0; i < leftCnt; i ++) {
		strncpy(node->keyArray[i], tempKey[i], 10);
		char tempChar[11] = {'\0'};
		strncpy(tempChar, tempKey[i], 10);
		node->ridArray[i] = map[std::string(tempChar)];
	}
	
	// clear left node
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->leafOccupancy - leftCnt + 1;

	// set sibling ptr
	newNode->rightSibPageNo = node->rightSibPageNo;
	node->rightSibPageNo = newNodePageId;
	char* ret_key = newNode->keyArray[0];

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, char*>(newNodePageId, ret_key);
}

// -----------------------------------------------------------------------------
// BTreeIndex::splitNonLeafNode -- split none leaf node
// -----------------------------------------------------------------------------

const std::pair<PageId, int*> BTreeIndex::splitNonLeafNodeInt(struct NonLeafNodeInt* node, PageId left, PageId right, int* key) {
	// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct NonLeafNodeInt* newNode = (NonLeafNodeInt*) newNodePage;
	memset(newNode->keyArray, 0, sizeof(newNode->keyArray));
	memset(newNode->pageNoArray, 0, sizeof(newNode->pageNoArray));
	int leftCnt = (this->nodeOccupancy) / 2;

	// map key to pageId
	std::unordered_map<int, PageId> map;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		map.insert({node->keyArray[i], node->pageNoArray[i + 1]});
	}
	map.insert({*key, right});

	int tempKey[this->nodeOccupancy + 1];
	bool inserted = false;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		if(*key < node->keyArray[i] && !inserted) {
			tempKey[i] = *key;
			inserted = true;
			continue;
		}

		if(!inserted) {
			tempKey[i] = node->keyArray[i];
		} else {
			tempKey[i] = node->keyArray[i - 1];
		}
	}
	if(!inserted) {
		tempKey[this->leafOccupancy] = *(int*)key;
	} else {
		tempKey[this->leafOccupancy] = node->keyArray[this->leafOccupancy - 1];
	}

	// fill in left and right node key/page no array
	for(int i = 0; i < this->nodeOccupancy - leftCnt; i ++) {
		newNode->keyArray[i] = tempKey[i + leftCnt + 1];
	}

	for(int i = 0; i < this->nodeOccupancy - leftCnt + 1; i ++) {
		newNode->pageNoArray[i] = map[tempKey[i + leftCnt]];
	}

	for(int i = 0; i < leftCnt; i ++) {
		node->keyArray[i] = tempKey[i];
	}

	for(int i = 1; i < leftCnt + 1; i ++) {
		node->pageNoArray[i] = map[tempKey[i - 1]];
	}

	// clear left node
	memset(node->keyArray + leftCnt, 0, (this->nodeOccupancy - leftCnt) * sizeof(int));
	memset(node->pageNoArray + leftCnt + 1, 0, (this->nodeOccupancy - leftCnt) * sizeof(PageId));
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->nodeOccupancy - leftCnt;
	newNode->level = node->level;
	int* ret_key = (int*)calloc(1, sizeof(int));
	*ret_key = tempKey[leftCnt];

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, int*>(newNodePageId, ret_key);
}

const std::pair<PageId, double*> BTreeIndex::splitNonLeafNodeDouble(struct NonLeafNodeDouble* node, PageId left, PageId right, double* key) {
	// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct NonLeafNodeDouble* newNode = (NonLeafNodeDouble*) newNodePage;
	memset(newNode->keyArray, 0, sizeof(newNode->keyArray));
	memset(newNode->pageNoArray, 0, sizeof(newNode->pageNoArray));
	int leftCnt = (this->nodeOccupancy) / 2;

	// map key to pageId
	std::unordered_map<double, PageId> map;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		map.insert({node->keyArray[i], node->pageNoArray[i + 1]});
	}
	map.insert({*key, right});

	double tempKey[this->nodeOccupancy + 1];
	bool inserted = false;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		if(*key < node->keyArray[i] && !inserted) {
			tempKey[i] = *key;
			inserted = true;
			continue;
		}

		if(!inserted) {
			tempKey[i] = node->keyArray[i];
		} else {
			tempKey[i] = node->keyArray[i - 1];
		}
	}
	if(!inserted) {
		tempKey[this->leafOccupancy] = *(double*)key;
	} else {
		tempKey[this->leafOccupancy] = node->keyArray[this->leafOccupancy - 1];
	}

	// fill in left and right node key/page no array
	for(int i = 0; i < this->nodeOccupancy - leftCnt; i ++) {
		newNode->keyArray[i] = tempKey[i + leftCnt + 1];
	}

	for(int i = 0; i < this->nodeOccupancy - leftCnt + 1; i ++) {
		newNode->pageNoArray[i] = map[tempKey[i + leftCnt]];
	}

	for(int i = 0; i < leftCnt; i ++) {
		node->keyArray[i] = tempKey[i];
	}

	for(int i = 1; i < leftCnt + 1; i ++) {
		node->pageNoArray[i] = map[tempKey[i - 1]];
	}

	// clear left node
	memset(node->keyArray + leftCnt, 0, (this->nodeOccupancy - leftCnt) * sizeof(double));
	memset(node->pageNoArray + leftCnt + 1, 0, (this->nodeOccupancy - leftCnt) * sizeof(PageId));
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->nodeOccupancy - leftCnt;
	newNode->level = node->level;
	double* ret_key = (double*)calloc(1, sizeof(double));
	*ret_key = tempKey[leftCnt];

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, double*>(newNodePageId, ret_key);
}

const std::pair<PageId, char*> BTreeIndex::splitNonLeafNodeString(struct NonLeafNodeString* node, PageId left, PageId right, char* key) {
		// allocate a new leaf
	Page* newNodePage;
	PageId newNodePageId;
	this->bufMgr->allocPage(this->file, newNodePageId, newNodePage);

	struct NonLeafNodeString* newNode = (NonLeafNodeString*) newNodePage;
	memset(newNode->pageNoArray, 0, sizeof(newNode->pageNoArray));
	int leftCnt = (this->nodeOccupancy) / 2;

	// map key to pageId
	std::unordered_map<std::string, PageId> map;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		char tempChar[11] = {'\0'};
		strncpy(tempChar, node->keyArray[i], 10);
		map.insert({std::string(tempChar), node->pageNoArray[i + 1]});
	}
	char tempChar[11] = {'\0'};
	strncpy(tempChar, key, 10);
	map.insert({std::string(tempChar), right});

	char tempKey[this->nodeOccupancy + 1][10];
	bool inserted = false;
	for(int i = 0; i < this->nodeOccupancy; i ++) {
		if(strncmp(key, node->keyArray[i], 10) < 0 && !inserted) {
			strncpy(tempKey[i], (char*)key, 10);
			inserted = true;
			continue;
		}

		if(!inserted) {
			strncpy(tempKey[i], node->keyArray[i], 10);
		} else {
			strncpy(tempKey[i], node->keyArray[i - 1], 10);
		}
	}
	if(!inserted) {
		strncpy(tempKey[this->leafOccupancy], (char*)key, 10);
	} else {
		strncpy(tempKey[this->leafOccupancy], node->keyArray[this->leafOccupancy - 1], 10);
	}

	// fill in left and right node key/page no array
	for(int i = 0; i < this->nodeOccupancy - leftCnt; i ++) {
		strncpy(newNode->keyArray[i], tempKey[i + leftCnt + 1], 10);
	}

	for(int i = 0; i < this->nodeOccupancy - leftCnt + 1; i ++) {
		char tempChar[11] = {'\0'};
		strncpy(tempChar, tempKey[i + leftCnt], 10);
		newNode->pageNoArray[i] = map[std::string(tempChar)];
	}

	for(int i = 0; i < leftCnt; i ++) {
		strncpy(node->keyArray[i], tempKey[i], 10);
	}

	for(int i = 1; i < leftCnt + 1; i ++) {
		char tempChar[11] = {'\0'};
		strncpy(tempChar, tempKey[i - 1], 10);
		node->pageNoArray[i] = map[std::string(tempChar)];
	}

	// clear left node
	memset(node->pageNoArray + leftCnt + 1, 0, (this->nodeOccupancy - leftCnt) * sizeof(PageId));
	node->keyArrLength = leftCnt;
	newNode->keyArrLength = this->nodeOccupancy - leftCnt;
	newNode->level = node->level;
	char* ret_key = (char*)calloc(10, sizeof(char));
	strncpy(ret_key, tempKey[leftCnt], 10);

	this->bufMgr->unPinPage(this->file, newNodePageId, true);
	return std::pair<PageId, char*>(newNodePageId, ret_key);
}

// -----------------------------------------------------------------------------
// BTreeIndex::insertRecursive
// -----------------------------------------------------------------------------
const std::pair<std::pair<PageId, PageId>, void*> BTreeIndex::insertRecursive(PageId root, const void *key, 
                                       const RecordId rid, int lastLevel) {
	// check if current node is leaf or not
	Page* node;
	this->bufMgr->readPage(this->file, root, node);

	if(this->attributeType == INTEGER) {
		if(lastLevel == 1) {
			// is a leaf
			struct LeafNodeInt* currNode = (LeafNodeInt*)node;
			
			// check whether to split
			if(currNode->keyArrLength < this->leafOccupancy) {
				insertIntKeyToLeaf(currNode, key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
				return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
			} else {
				// split
				std::pair<PageId, int*> res = splitLeafNodeInt(currNode, (int*)key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
			    return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																  (void*)res.second);
			}
		} else {
			// not a leaf
			PageId nextNodeId = findPageNoInNonLeaf(node, key);
			struct NonLeafNodeInt* currNode = (NonLeafNodeInt*)node;
			int cur_level = currNode->level;
			this->bufMgr->unPinPage(this->file, root, false);
			std::pair<std::pair<PageId, PageId>, void*> insertRes = insertRecursive(nextNodeId, key, rid, cur_level);
			
			// read page
			this->bufMgr->readPage(this->file, root, node);
			currNode = (NonLeafNodeInt*)node; 

			// return from recursive call, propagate up
			if(insertRes.second != NULL) {
				if(currNode->keyArrLength < this->nodeOccupancy) {
					insertIntKeyToNonLeaf(currNode,(int*)insertRes.second, 
					                      insertRes.first.second);
					//free((int*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
				} else { 
					std::pair<PageId, int*> res = splitNonLeafNodeInt(currNode, insertRes.first.first, 
					                                                  insertRes.first.second, (int*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																      (void*)res.second);
				}
			}
			this->bufMgr->unPinPage(this->file, root, false);
			return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
		}
	} else if(this->attributeType == DOUBLE) {
		if(lastLevel == 1) {
			// is a leaf
			struct LeafNodeDouble* currNode = (LeafNodeDouble*)node;
			
			// check whether to split
			if(currNode->keyArrLength < this->leafOccupancy) {
				insertDoubleKeyToLeaf(currNode, key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
				return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
			} else {
				// split
				std::pair<PageId, double*> res = splitLeafNodeDouble(currNode, (double*)key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
			    return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																  (void*)res.second);
			}
		} else {
			// not a leaf
			PageId nextNodeId = findPageNoInNonLeaf(node, key);
			struct NonLeafNodeDouble* currNode = (NonLeafNodeDouble*)node;
			int cur_level = currNode->level;
			this->bufMgr->unPinPage(this->file, root, false);
			std::pair<std::pair<PageId, PageId>, void*> insertRes = insertRecursive(nextNodeId, key, rid, cur_level);
			
			// read page
			this->bufMgr->readPage(this->file, root, node);
			currNode = (NonLeafNodeDouble*)node; 

			// return from recursive call, propagate up
			if(insertRes.second != NULL) {
				if(currNode->keyArrLength < this->nodeOccupancy) {
					insertDoubleKeyToNonLeaf(currNode,(double*)insertRes.second, 
					                         insertRes.first.second);
					//free((int*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
				} else { 
					std::pair<PageId, double*> res = splitNonLeafNodeDouble(currNode, insertRes.first.first, 
					                                                  insertRes.first.second, (double*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																      (void*)res.second);
				}
			}
			this->bufMgr->unPinPage(this->file, root, false);
			return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
		}
	} else {
		if(lastLevel == 1) {
			// is a leaf
			struct LeafNodeString* currNode = (LeafNodeString*)node;
			// check whether to split
			if(currNode->keyArrLength < this->leafOccupancy) {
				insertStringKeyToLeaf(currNode, key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
				return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
			} else {
				// split
				std::pair<PageId, char*> res = splitLeafNodeString(currNode, (char*)key, rid);
				this->bufMgr->unPinPage(this->file, root, true);
			    return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																  (void*)res.second);
			}
		} else {
		    // not a leaf
			PageId nextNodeId = findPageNoInNonLeaf(node, key);
			struct NonLeafNodeString* currNode = (NonLeafNodeString*)node;
			int cur_level = currNode->level;
			this->bufMgr->unPinPage(this->file, root, false);
			std::pair<std::pair<PageId, PageId>, void*> insertRes = insertRecursive(nextNodeId, key, rid, cur_level);
			
			// read page
			this->bufMgr->readPage(this->file, root, node);
			currNode = (NonLeafNodeString*)node; 

			// return from recursive call, propagate up
			if(insertRes.second != NULL) {
				if(currNode->keyArrLength < this->nodeOccupancy) {
					insertStringKeyToNonLeaf(currNode,(char*)insertRes.second, 
					                         insertRes.first.second);
					//free((int*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
				} else { 
					std::pair<PageId, char*> res = splitNonLeafNodeString(currNode, insertRes.first.first, 
					                                                      insertRes.first.second, (char*)insertRes.second);
					this->bufMgr->unPinPage(this->file, root, true);
					return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, res.first), 
																      (void*)res.second);
				}
			}
			this->bufMgr->unPinPage(this->file, root, false);
			return std::pair<std::pair<PageId, PageId>, void*>(std::pair<PageId, PageId>(root, 0), NULL);
		}
	}
}

// -----------------------------------------------------------------------------
// BTreeIndex::insertEntry
// -----------------------------------------------------------------------------

const void BTreeIndex::insertEntry(const void *key, const RecordId rid) {
	Page* rootPage;
	this->bufMgr->readPage(this->file, this->rootPageNum, rootPage);
	Page* leafPage;
	PageId leafPageId;
	Page* leafPageLeft;
	PageId leafPageLeftId;

	// init root node leaf
	if(this->attributeType == INTEGER) {
		struct NonLeafNodeInt* rootNode = (NonLeafNodeInt*) rootPage;
		if(rootNode->keyArrLength == 0) {
			rootNode->keyArray[0] = *((int*)key);
			
			// allocate right leaf
			this->bufMgr->allocPage(this->file, leafPageId, leafPage);
			rootNode->pageNoArray[1] = leafPageId;
			rootNode->keyArrLength ++;
			
			// allocate left leaf
			this->bufMgr->allocPage(this->file, leafPageLeftId, leafPageLeft);
			rootNode->pageNoArray[0] = leafPageLeftId;
			((LeafNodeInt*)leafPageLeft)->rightSibPageNo = leafPageId;
			this->bufMgr->unPinPage(this->file, leafPageId, true);
			this->bufMgr->unPinPage(this->file, leafPageLeftId, true);
		}
	} else if(this->attributeType == DOUBLE) {
		struct NonLeafNodeDouble* rootNode = (NonLeafNodeDouble*) rootPage;
		if(rootNode->keyArrLength == 0) {
			rootNode->keyArray[0] = *((double*)key);
			
			// allocate leaf
			this->bufMgr->allocPage(this->file, leafPageId, leafPage);
			rootNode->pageNoArray[1] = leafPageId;
			rootNode->keyArrLength ++;

			// allocate left leaf
			this->bufMgr->allocPage(this->file, leafPageLeftId, leafPageLeft);
			rootNode->pageNoArray[0] = leafPageLeftId;
			((LeafNodeDouble*)leafPageLeft)->rightSibPageNo = leafPageId;
			this->bufMgr->unPinPage(this->file, leafPageId, true);
			this->bufMgr->unPinPage(this->file, leafPageLeftId, true);
		}

	} else {
		struct NonLeafNodeString* rootNode = (NonLeafNodeString*) rootPage;
		if(rootNode->keyArrLength == 0) {
			strncpy(rootNode->keyArray[0], (char*)key, 10);
			
			// allocate leaf
			this->bufMgr->allocPage(this->file, leafPageId, leafPage);
			rootNode->pageNoArray[1] = leafPageId;
			rootNode->keyArrLength ++;

			// allocate left leaf
			this->bufMgr->allocPage(this->file, leafPageLeftId, leafPageLeft);
			rootNode->pageNoArray[0] = leafPageLeftId;
			((LeafNodeString*)leafPageLeft)->rightSibPageNo = leafPageId;
			this->bufMgr->unPinPage(this->file, leafPageId, true);
			this->bufMgr->unPinPage(this->file, leafPageLeftId, true);
		}
	}

	// recursively insert
	std::pair<std::pair<PageId, PageId>, void*> res = insertRecursive(this->rootPageNum, key, rid, 0);
	
	// root is split
	if(res.second != NULL) {
		Page* newRootPage;
		PageId newRootPageId;
		this->bufMgr->allocPage(this->file, newRootPageId, newRootPage);
		
		if(this->attributeType == INTEGER) {
			NonLeafNodeInt* rootNode = (NonLeafNodeInt*)newRootPage;
			rootNode->keyArray[0] = *((int*)res.second);
			rootNode->pageNoArray[0] = res.first.first;
			rootNode->pageNoArray[1] = res.first.second;
			rootNode->level = 0;
			rootNode->keyArrLength = 1;
			this->rootPageNum = newRootPageId;
			
			// set index meta page
			Page* headerPage;
			this->bufMgr->readPage(this->file, this->headerPageNum, headerPage);
			struct IndexMetaInfo* temp = (IndexMetaInfo*)headerPage;
			temp->rootPageNo = newRootPageId;	
		} else if(this->attributeType == DOUBLE) {
			NonLeafNodeDouble* rootNode = (NonLeafNodeDouble*)newRootPage;
			rootNode->keyArray[0] = *((double*)res.second);
			rootNode->pageNoArray[0] = res.first.first;
			rootNode->pageNoArray[1] = res.first.second;
			rootNode->level = 0;
			rootNode->keyArrLength = 1;
			this->rootPageNum = newRootPageId;
			
			// set index meta page
			Page* headerPage;
			this->bufMgr->readPage(this->file, this->headerPageNum, headerPage);
			struct IndexMetaInfo* temp = (IndexMetaInfo*)headerPage;
			temp->rootPageNo = newRootPageId;	
		} else {
			NonLeafNodeString* rootNode = (NonLeafNodeString*)newRootPage;
			strncpy(rootNode->keyArray[0], (char*)res.second, 10);
			rootNode->pageNoArray[0] = res.first.first;
			rootNode->pageNoArray[1] = res.first.second;
			rootNode->level = 0;
			rootNode->keyArrLength = 1;
			this->rootPageNum = newRootPageId;
			
			// set index meta page
			Page* headerPage;
			this->bufMgr->readPage(this->file, this->headerPageNum, headerPage);
			struct IndexMetaInfo* temp = (IndexMetaInfo*)headerPage;
			temp->rootPageNo = newRootPageId;	
		}
	}
}

// -----------------------------------------------------------------------------
// BTreeIndex::startScan
// -----------------------------------------------------------------------------

const void BTreeIndex::startScan(const void* lowValParm,
				   const Operator lowOpParm,
				   const void* highValParm,
				   const Operator highOpParm) {
	// check op
	if((lowOpParm != GT && lowOpParm != GTE) || (highOpParm != LT && highOpParm != LTE)) {
		throw BadOpcodesException();
	}

	// if lowVal > highVal throw exception
	if(this->attributeType == INTEGER) {
		int lowVal = *((int*)lowValParm);
		int highVal = *((int*)highValParm);
		if(lowVal > highVal) throw BadScanrangeException();
		
		this->lowValInt = lowVal;
		this->highValInt = highVal;
		this->lowOp = lowOpParm;
		this->highOp = highOpParm;

		PageId rootPageId = this->rootPageNum;
		Page* rootPage;
		this->bufMgr->readPage(this->file, rootPageId, rootPage);
		NonLeafNodeInt* rootPageNode = (NonLeafNodeInt*)rootPage;

		// find leaf
		while(rootPageNode->level != 1) {
			PageId nextNodeId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
			this->bufMgr->unPinPage(this->file, rootPageId, false);
			rootPageId = nextNodeId;
			this->bufMgr->readPage(this->file, rootPageId, rootPage);
			rootPageNode = (NonLeafNodeInt*)rootPage;
		}
		PageId leafId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
		this->bufMgr->unPinPage(this->file, rootPageId, false);


		// find whether value is there
		Page* leafPage;
		this->bufMgr->readPage(this->file, leafId, leafPage);
		LeafNodeInt* leafNode = (LeafNodeInt*)leafPage;
		for(int i = 0; i < leafNode->keyArrLength; i ++) {
			if((lowOpParm == GT && leafNode->keyArray[i] > lowVal) || 
			   (lowOpParm == GTE && leafNode->keyArray[i] >= lowVal)) {
				this->nextEntry = i;
				this->currentPageNum = leafId;
				this->currentPageData = leafPage;
				this->scanExecuting = true;
				return;
			}
		}

		if(leafNode->rightSibPageNo == 0) {
			this->scanExecuting = false;
			throw NoSuchKeyFoundException();
		}

		Page* rightPage;
		this->bufMgr->readPage(this->file, leafNode->rightSibPageNo, rightPage);
		this->nextEntry = 0;
		this->currentPageNum = leafNode->rightSibPageNo;
		this->currentPageData = rightPage;
		this->scanExecuting = true;
		this->bufMgr->unPinPage(this->file, leafId, false);

	} else if(this->attributeType == DOUBLE) {
		double lowVal = *((double*)lowValParm);
		double highVal = *((double*)highValParm);
		if(lowVal > highVal) throw BadScanrangeException();
		
		this->lowValDouble = lowVal;
		this->highValDouble = highVal;
		this->lowOp = lowOpParm;
		this->highOp = highOpParm;

		PageId rootPageId = this->rootPageNum;
		Page* rootPage;
		this->bufMgr->readPage(this->file, rootPageId, rootPage);
		NonLeafNodeDouble* rootPageNode = (NonLeafNodeDouble*)rootPage;

		// find leaf
		while(rootPageNode->level != 1) {
			PageId nextNodeId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
			this->bufMgr->unPinPage(this->file, rootPageId, false);
			rootPageId = nextNodeId;
			this->bufMgr->readPage(this->file, rootPageId, rootPage);
			rootPageNode = (NonLeafNodeDouble*)rootPage;
		}
		PageId leafId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
		this->bufMgr->unPinPage(this->file, rootPageId, false);

		// find whether value is there
		Page* leafPage;
		this->bufMgr->readPage(this->file, leafId, leafPage);
		LeafNodeDouble* leafNode = (LeafNodeDouble*)leafPage;
		for(int i = 0; i < leafNode->keyArrLength; i ++) {
			if((lowOpParm == GT && leafNode->keyArray[i] > lowVal) || 
			   (lowOpParm == GTE && leafNode->keyArray[i] >= lowVal)) {
				this->nextEntry = i;
				this->currentPageNum = leafId;
				this->currentPageData = leafPage;
				this->scanExecuting = true;
				return;
			}
		}

		if(leafNode->rightSibPageNo == 0) {
			this->scanExecuting = false;
			throw NoSuchKeyFoundException();
		}

		Page* rightPage;
		this->bufMgr->readPage(this->file, leafNode->rightSibPageNo, rightPage);
		this->nextEntry = 0;
		this->currentPageNum = leafNode->rightSibPageNo;
		this->currentPageData = rightPage;
		this->scanExecuting = true;
		this->bufMgr->unPinPage(this->file, leafId, false);
	} else {
		char* lowVal = (char*)lowValParm;
		char* highVal = (char*)highValParm;
		if(strncmp(lowVal, highVal, 10) > 0) throw BadScanrangeException();
		
		strncpy(this->lowValString, lowVal, 10);
		strncpy(this->highValString, highVal, 10);
		this->lowOp = lowOpParm;
		this->highOp = highOpParm;

		PageId rootPageId = this->rootPageNum;
		Page* rootPage;
		this->bufMgr->readPage(this->file, rootPageId, rootPage);
		NonLeafNodeString* rootPageNode = (NonLeafNodeString*)rootPage;

		// find leaf
		while(rootPageNode->level != 1) {
			PageId nextNodeId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
			this->bufMgr->unPinPage(this->file, rootPageId, false);
			rootPageId = nextNodeId;
			this->bufMgr->readPage(this->file, rootPageId, rootPage);
			rootPageNode = (NonLeafNodeString*)rootPage;
		}
		PageId leafId = findPageNoInNonLeaf(rootPage, (void*)lowValParm);
		this->bufMgr->unPinPage(this->file, rootPageId, false);

		// find whether value is there
		Page* leafPage;
		this->bufMgr->readPage(this->file, leafId, leafPage);
		LeafNodeString* leafNode = (LeafNodeString*)leafPage;
		for(int i = 0; i < leafNode->keyArrLength; i ++) {
			if((lowOpParm == GT && strncmp(leafNode->keyArray[i], lowVal, 10) > 0) || 
			   (lowOpParm == GTE && strncmp(leafNode->keyArray[i], lowVal, 10) >= 0)) {
				this->nextEntry = i;
				this->currentPageNum = leafId;
				this->currentPageData = leafPage;
				this->scanExecuting = true;
				return;
			}
		}

		if(leafNode->rightSibPageNo == 0) {
			this->scanExecuting = false;
			throw NoSuchKeyFoundException();
		}

		Page* rightPage;
		this->bufMgr->readPage(this->file, leafNode->rightSibPageNo, rightPage);
		this->nextEntry = 0;
		this->currentPageNum = leafNode->rightSibPageNo;
		this->currentPageData = rightPage;
		this->scanExecuting = true;
		this->bufMgr->unPinPage(this->file, leafId, false);
	}

}

// -----------------------------------------------------------------------------
// BTreeIndex::scanNext
// -----------------------------------------------------------------------------

const void BTreeIndex::scanNext(RecordId& outRid) {
	if(!this->scanExecuting) throw ScanNotInitializedException();

	if(this->attributeType == INTEGER) {
		LeafNodeInt* leaf = (LeafNodeInt*)this->currentPageData;
		if(this->nextEntry == leaf->keyArrLength && leaf->rightSibPageNo == 0) {
			throw IndexScanCompletedException();
		}

		if((this->highOp == LT && leaf->keyArray[this->nextEntry] < this->highValInt) ||
		    (this->highOp == LTE && leaf->keyArray[this->nextEntry] <= this->highValInt)) {	
			outRid = leaf->ridArray[this->nextEntry];
			nextEntry ++;

			// if reach right bound of leaf, jump to sibling
			if(this->nextEntry == leaf->keyArrLength) {
				Page* rightPage;
				if(leaf->rightSibPageNo == 0) return;
				this->bufMgr->readPage(this->file, leaf->rightSibPageNo, rightPage);
				PageId leafId = this->currentPageNum;
				this->nextEntry = 0;
				this->currentPageNum = leaf->rightSibPageNo;
				this->currentPageData = rightPage;
				this->bufMgr->unPinPage(this->file, leafId, false);
		    }
			return;
		}
		throw IndexScanCompletedException();
	} else if(this->attributeType == DOUBLE) {
		LeafNodeDouble* leaf = (LeafNodeDouble*)this->currentPageData;
		if(this->nextEntry == leaf->keyArrLength && leaf->rightSibPageNo == 0) {
			throw IndexScanCompletedException();
		}

		if((this->highOp == LT && leaf->keyArray[this->nextEntry] < this->highValDouble) ||
		    (this->highOp == LTE && leaf->keyArray[this->nextEntry] <= this->highValDouble)) {	
			outRid = leaf->ridArray[this->nextEntry];
			nextEntry ++;
			
			// if reach right bound of leaf, jump to sibling
			if(this->nextEntry == leaf->keyArrLength) {
				Page* rightPage;
				if(leaf->rightSibPageNo == 0) return;
				this->bufMgr->readPage(this->file, leaf->rightSibPageNo, rightPage);
				PageId leafId = this->currentPageNum;
				this->nextEntry = 0;
				this->currentPageNum = leaf->rightSibPageNo;
				this->currentPageData = rightPage;
				this->bufMgr->unPinPage(this->file, leafId, false);
		    }
			return;
		}
		throw IndexScanCompletedException();
	} else {
		LeafNodeString* leaf = (LeafNodeString*)this->currentPageData;
		if(this->nextEntry == leaf->keyArrLength && leaf->rightSibPageNo == 0) {
			throw IndexScanCompletedException();
		}
		
		if((this->highOp == LT && strncmp(leaf->keyArray[this->nextEntry], this->highValString, 10) < 0) ||
		    (this->highOp == LTE && strncmp(leaf->keyArray[this->nextEntry], this->highValString, 10) <= 0)) {	
			outRid = leaf->ridArray[this->nextEntry];
			nextEntry ++;
			
			// if reach right bound of leaf, jump to sibling
			if(this->nextEntry == leaf->keyArrLength) {
				Page* rightPage;
				if(leaf->rightSibPageNo == 0) return;
				this->bufMgr->readPage(this->file, leaf->rightSibPageNo, rightPage);
				PageId leafId = this->currentPageNum;
				this->nextEntry = 0;
				this->currentPageNum = leaf->rightSibPageNo;
				this->currentPageData = rightPage;
				this->bufMgr->unPinPage(this->file, leafId, false);
		    }
		
			return;
		}
		throw IndexScanCompletedException();
	}
}

// -----------------------------------------------------------------------------
// BTreeIndex::endScan
// -----------------------------------------------------------------------------
//
const void BTreeIndex::endScan() {
	if(!this->scanExecuting) throw ScanNotInitializedException();
	this->scanExecuting = false;
	this->bufMgr->unPinPage(this->file, this->currentPageNum, false);
}

}
