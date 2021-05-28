#include <iostream>
#include <stdlib.h>
//#include <stdio.h>
#include <cstring>
#include <memory>
#include "page.h"
#include "buffer.h"
#include "file_iterator.h"
#include "page_iterator.h"
#include "exceptions/file_not_found_exception.h"
#include "exceptions/invalid_page_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "exceptions/buffer_exceeded_exception.h"

#define PRINT_ERROR(str) \
{ \
	std::cerr << "On Line No:" << __LINE__ << "\n"; \
	std::cerr << str << "\n"; \
	exit(1); \
}

using namespace badgerdb;

const PageId num = 100;
PageId pid[num], pid8[num * 2], pid9[num], pid10a[num], pid10b[num], pid12[num], pid12f[num], pageno1, pageno2, pageno3, i;
RecordId rid[num], rid8[num * 2], rid9[num], rid10a[num], rid10b[num], rid12[num], rid12f[num], rid2, rid3;
Page *page, *page2, *page3;
char tmpbuf[100];
BufMgr* bufMgr;
File *file1ptr, *file2ptr, *file3ptr, *file4ptr, *file5ptr, *file7ptr, *file8ptr, *file9ptr, *file10aptr, *file10bptr, *file12ptr;

void test1();
void test2();
void test3();
void test4();
void test5();
void test6();
void test7();
void test8();
void test9();
void test10();
void test11();
void test12();
void testBufMgr();

int main() 
{
	//Following code shows how to you File and Page classes
  std::cout << "Starting tests\n";
  const std::string& filename = "test.db";
  // Clean up from any previous runs that crashed.
  try
	{
    File::remove(filename);
  }
	catch(FileNotFoundException)
	{
  }

  {
    // Create a new database file.
    File new_file = File::create(filename);
    
    // Allocate some pages and put data on them.
    PageId third_page_number;
    for (int i = 0; i < 5; ++i) {
      Page new_page = new_file.allocatePage();
      if (i == 3) {
        // Keep track of the identifier for the third page so we can read it
        // later.
        third_page_number = new_page.page_number();
      }
      new_page.insertRecord("hello!");
      // Write the page back to the file (with the new data).
      new_file.writePage(new_page);
    }

    // Iterate through all pages in the file.
    for (FileIterator iter = new_file.begin();
         iter != new_file.end();
         ++iter) {
      // Iterate through all records on the page.
      for (PageIterator page_iter = (*iter).begin();
           page_iter != (*iter).end();
           ++page_iter) {
        std::cout << "Found record: " << *page_iter
            << " on page " << (*iter).page_number() << "\n";
      }
    }

    // Retrieve the third page and add another record to it.
    Page third_page = new_file.readPage(third_page_number);
    const RecordId& rid = third_page.insertRecord("world!");
    new_file.writePage(third_page);

    // Retrieve the record we just added to the third page.
    std::cout << "Third page has a new record: "
        << third_page.getRecord(rid) << "\n\n";
  }
  // new_file goes out of scope here, so file is automatically closed.

  // Delete the file since we're done with it.
  File::remove(filename);
	
  //This function tests buffer manager, comment this line if you don't wish to test buffer manager
  testBufMgr();
}

void testBufMgr()
{
	// create buffer manager
	bufMgr = new BufMgr(num);

	// create dummy files
  const std::string& filename1 = "test.1";
  const std::string& filename2 = "test.2";
  const std::string& filename3 = "test.3";
  const std::string& filename4 = "test.4";
  const std::string& filename5 = "test.5";
  const std::string& filename7 = "test.7";
  const std::string& filename8 = "test.8";
  const std::string& filename9 = "test.9";
  const std::string& filename10a = "test.10a";
  const std::string& filename10b = "test.10b";
  const std::string& filename12 = "test.12";

  try
	{
    File::remove(filename1);
    File::remove(filename2);
    File::remove(filename3);
    File::remove(filename4);
    File::remove(filename5);
	File::remove(filename7);
	File::remove(filename8);
	File::remove(filename9);
	File::remove(filename10a);
	File::remove(filename10b);
	File::remove(filename12);
  }
	catch(FileNotFoundException e)
	{
  }

	File file1 = File::create(filename1);
	File file2 = File::create(filename2);
	File file3 = File::create(filename3);
	File file4 = File::create(filename4);
	File file5 = File::create(filename5);
	File file7 = File::create(filename7);
	File file8 = File::create(filename8);
	File file9 = File::create(filename9);
	File file10a = File::create(filename10a);
	File file10b = File::create(filename10b);
	File file12 = File::create(filename12);

	file1ptr = &file1;
	file2ptr = &file2;
	file3ptr = &file3;
	file4ptr = &file4;
	file5ptr = &file5;
	file7ptr = &file7;
	file8ptr = &file8;
	file9ptr = &file9;
	file10aptr = &file10a;
	file10bptr = &file10b;
	file12ptr = &file12;

	//Test buffer manager
	//Comment tests which you do not wish to run now. Tests are dependent on their preceding tests. So, they have to be run in the following order. 
	//Commenting  a particular test requires commenting all tests that follow it else those tests would fail.
	test1();
	test2();
	test3();
	test4();
	test5();
	test6();
	test7();
	test8();
	test9();
	test10();
	test11();
	test12();

	//Close files before deleting them
	file1.~File();
	file2.~File();
	file3.~File();
	file4.~File();
	file5.~File();
	file7.~File();
	file8.~File();
	file9.~File();
	file10a.~File();
	file10b.~File();
	file12.~File();

	//Delete files
	File::remove(filename1);
	File::remove(filename2);
	File::remove(filename3);
	File::remove(filename4);
	File::remove(filename5);
	File::remove(filename7);
	File::remove(filename8);
	File::remove(filename9);
	File::remove(filename10a);
	File::remove(filename10b);
	File::remove(filename12);

	delete bufMgr;

	std::cout << "\n" << "Passed all tests." << "\n";
}

/**
 * Test 1:
 *     Allocate pages and read back
 * 
 * Allocated pages should be able to be read back
 */
void test1()
{
	//Allocating pages in a file...
	for (i = 0; i < num; i++)
	{	
		bufMgr->allocPage(file1ptr, pid[i], page);
		sprintf((char*)tmpbuf, "test.1 Page %d %7.1f", pid[i], (float)pid[i]);
		rid[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file1ptr, pid[i], true);
	}
	
	//Reading pages back...
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file1ptr, pid[i], page);
		sprintf((char*)&tmpbuf, "test.1 Page %d %7.1f", pid[i], (float)pid[i]);
		if(strncmp(page->getRecord(rid[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		bufMgr->unPinPage(file1ptr, pid[i], false);
	}

	std::cout<< "Test 1 passed" << "\n";
}

/**
 * Test 2:
 *     Writing and reading back multiple files
 * 
 * Writing a file and then reading it back should give the same contents
 * i.e. contents match for every page written
 */
void test2()
{
	// Writing and reading back multiple files
	// The page number and the value should match
	for (i = 0; i < num/3; i++) 
	{
		bufMgr->allocPage(file2ptr, pageno2, page2);
		sprintf((char*)tmpbuf, "test.2 Page %d %7.1f", pageno2, (float)pageno2);
		rid2 = page2->insertRecord(tmpbuf);
		
		int index = random() % num;
    	pageno1 = pid[index];
		bufMgr->readPage(file1ptr, pageno1, page);
		sprintf((char*)tmpbuf, "test.1 Page %d %7.1f", pageno1, (float)pageno1);
		if(strncmp(page->getRecord(rid[index]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		
		bufMgr->allocPage(file3ptr, pageno3, page3);
		sprintf((char*)tmpbuf, "test.3 Page %d %7.1f", pageno3, (float)pageno3);
		rid3 = page3->insertRecord(tmpbuf);

		bufMgr->readPage(file2ptr, pageno2, page2);
		sprintf((char*)&tmpbuf, "test.2 Page %d %7.1f", pageno2, (float)pageno2);
		if(strncmp(page2->getRecord(rid2).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		
		bufMgr->readPage(file3ptr, pageno3, page3);
		sprintf((char*)&tmpbuf, "test.3 Page %d %7.1f", pageno3, (float)pageno3);
		if(strncmp(page3->getRecord(rid3).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}

		bufMgr->unPinPage(file1ptr, pageno1, false);
	}

	for (i = 0; i < num/3; i++) {
		bufMgr->unPinPage(file2ptr, i+1, true);
		bufMgr->unPinPage(file2ptr, i+1, true);
		bufMgr->unPinPage(file3ptr, i+1, true);
		bufMgr->unPinPage(file3ptr, i+1, true);
	}

	std::cout << "Test 2 passed" << "\n";
}

/**
 * Test 3:
 *     Read file that does not exist
 * 
 * Reading a inexistent file should give an exception
 */
void test3()
{
	try
	{
		bufMgr->readPage(file4ptr, 1, page);
		PRINT_ERROR("ERROR :: File4 should not exist. Exception should have been thrown before execution reaches this point.");
	}
	catch(InvalidPageException e)
	{
	}

	std::cout << "Test 3 passed" << "\n";
}

/**
 * Test 4 (more likely the actual Test 11):
 *     Unpin pinned pages and unpin unpinned pages
 * 
 * Unpinning pinned pages should mark the page's pin count to 0
 * whereas unpinning a pinned page results in an exception
 */
void test4()
{
	bufMgr->allocPage(file4ptr, i, page);
	bufMgr->unPinPage(file4ptr, i, true);
	try
	{
		bufMgr->unPinPage(file4ptr, i, false);
		PRINT_ERROR("ERROR :: Page is already unpinned. Exception should have been thrown before execution reaches this point.");
	}
	catch(PageNotPinnedException e)
	{
	}

	std::cout << "Test 4 passed" << "\n";
}

/**
 * Test 5:
 *     No more frames in buffer pool left for allocation
 *     (i.e. all pages in buffer pool are pinned)
 * 
 * Not being able to allocate frame for new page should result in an exception
 */
void test5()
{
	for (i = 0; i < num; i++) {
		bufMgr->allocPage(file5ptr, pid[i], page);
		sprintf((char*)tmpbuf, "test.5 Page %d %7.1f", pid[i], (float)pid[i]);
		rid[i] = page->insertRecord(tmpbuf);
	}

	PageId tmp;
	try
	{
		bufMgr->allocPage(file5ptr, tmp, page);
		PRINT_ERROR("ERROR :: No more frames left for allocation. Exception should have been thrown before execution reaches this point.");
	}
	catch(BufferExceededException e)
	{
	}

	std::cout << "Test 5 passed" << "\n";

	for (i = 1; i <= num; i++)
		bufMgr->unPinPage(file5ptr, i, true);
}

/**
 * Test 6:
 *     Flush pinned page
 * 
 * Trying to flush pinned pages from a file results in an exception
 */
void test6()
{
	// flushing file with pages still pinned. Should generate an error
	for (i = 1; i <= num; i++) {
		bufMgr->readPage(file1ptr, i, page);
	}

	try
	{
		bufMgr->flushFile(file1ptr);
		PRINT_ERROR("ERROR :: Pages pinned for file being flushed. Exception should have been thrown before execution reaches this point.");
	}
	catch(PagePinnedException e)
	{
	}

	std::cout << "Test 6 passed" << "\n";

	for (i = 1; i <= num; i++) 
		bufMgr->unPinPage(file1ptr, i, true);

	bufMgr->flushFile(file1ptr);
}

/**
 * Test 7:
 *     Allocate pages, flush to disk and read back
 * 
 * Flushing pages to disk and then reading them back should not
 * modify contents in these pages
 */
void test7()
{
	// Allocating pages in a file...
	for (i = 0; i < num; i++)
	{	
		bufMgr->allocPage(file7ptr, pid[i], page);
		sprintf((char*)tmpbuf, "test.7 Page %d %7.1f", pid[i], (float)pid[i]);
		rid[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file7ptr, pid[i], true);
	}

	// flush pages to disk
	bufMgr->flushFile(file7ptr);
	
	//Reading pages back...
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file7ptr, pid[i], page);
		sprintf((char*)&tmpbuf, "test.7 Page %d %7.1f", pid[i], (float)pid[i]);
		if(strncmp(page->getRecord(rid[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		bufMgr->unPinPage(file7ptr, pid[i], false);
	}

	std::cout<< "Test 7 passed" << "\n";
}

/**
 * Test 8:
 *     Allocate pages that are more than available space in buffer pool
 *     and read back
 * 
 * Having number of pages more than what the buffer pool can hold
 * should not cause a problem (unless everything in the pool is pinned)
 */
void test8()
{
	// Allocating pages more than available in the buffer pool
	for (i = 0; i < 2 * num; i++)
	{	
		bufMgr->allocPage(file8ptr, pid8[i], page);
		sprintf((char*)tmpbuf, "test.8 Page %d %7.1f", pid8[i], (float)pid8[i]);
		rid8[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file8ptr, pid8[i], true);
	}
	
	//Reading pages back...
	for (i = 0; i < 2 * num; i++)
	{
		bufMgr->readPage(file8ptr, pid8[i], page);
		sprintf((char*)&tmpbuf, "test.8 Page %d %7.1f", pid8[i], (float)pid8[i]);
		if(strncmp(page->getRecord(rid8[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		bufMgr->unPinPage(file8ptr, pid8[i], false);
	}

	std::cout<< "Test 8 passed" << "\n";
}

/**
 * Test 9:
 *     Read pages after pages have been disposed
 * 
 * Trying to read a disposed page should result in an exception
 */
void test9() {
	// allocate new page
	bufMgr->allocPage(file9ptr, pid9[0], page);
	sprintf((char*)tmpbuf, "test.9 Page %d %7.1f", pid9[0], (float)pid9[0]);
	rid9[0] = page->insertRecord(tmpbuf);
	bufMgr->unPinPage(file9ptr, pid9[0], true);

	// dispose page
	bufMgr->disposePage(file9ptr, pid9[0]);

	// read page pack
	try {
		Page* temp_page_ptr;
		bufMgr->readPage(file9ptr, pid9[0], temp_page_ptr);
	} catch (InvalidPageException& e) {
		std::cout << "Test 9 passed" << std::endl;
	}
}

/**
 * Test 10:
 *     Read pages after allocating pages for another file
 * 
 * Allocating pages for another file should not modify contents of this one
 */
void test10()
{
	// Allocating pages in file 1...
	for (i = 0; i < num; i++)
	{	
		bufMgr->allocPage(file10aptr, pid10a[i], page);
		sprintf((char*)tmpbuf, "test.10a Page %d %7.1f", pid10a[i], (float)pid10a[i]);
		rid10a[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file10aptr, pid10a[i], true);
	}

	// Allocating pages in file 2
	for (i = 0; i < num; i++)
	{	
		bufMgr->allocPage(file10bptr, pid10b[i], page);
		sprintf((char*)tmpbuf, "test.10b Page %d %7.1f", pid10b[i], (float)pid10b[i]);
		rid10b[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file10bptr, pid10b[i], true);
	}
	
	// Reading pages back from file 1...
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file10aptr, pid10a[i], page);
		sprintf((char*)&tmpbuf, "test.10a Page %d %7.1f", pid10a[i], (float)pid10a[i]);
		if(strncmp(page->getRecord(rid10a[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		bufMgr->unPinPage(file10aptr, pid10a[i], false);
	}

	std::cout << "Test 10 passed" << "\n";
}

/**
 * Test 11:
 *     Unpin pinned pages and unpin unpinned pages
 * 
 * This is actually tested by Test 4
 * Here, the test only ensures that trying to unpin unpinned pages
 * results in an exception
 */
void test11() {
	try {
		bufMgr->unPinPage(file10aptr, pid10a[0], false);
	} catch (PageNotPinnedException& e) {
		std::cout << "Test 11 passed" << "\n";
	}
}

/**
 * Test 12:
 *     Test dirty bit of unPinPage()
 * 
 * After unPinPage(_file, _PageNo, _dirty), the page in _file
 * with page number _PageNo should have the dirty bit set to
 * true **if** _dirty == true
 * The dirty bit should not change when _dirty == false
 */

void test12() {
	// Allocating pages in file...
	for (i = 0; i < num; i++)
	{	
		bufMgr->allocPage(file12ptr, pid12[i], page);
		sprintf((char*)tmpbuf, "test.12a Page %d %7.1f", pid12[i], (float)pid12[i]);
		rid12[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file12ptr, pid12[i], true);
	}

	// Flush pages just written to disk
	bufMgr->flushFile(file12ptr);

	// Check if writes reflected in file
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file12ptr, pid12[i], page);
		sprintf((char*)&tmpbuf, "test.12a Page %d %7.1f", pid12[i], (float)pid12[i]);
		if(strncmp(page->getRecord(rid12[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
		bufMgr->unPinPage(file12ptr, pid12[i], false);
	}

	// Try writting to these pages again with different contents
	// yet setting dirty bit to false
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file12ptr, pid12[i], page);
		page->deleteRecord(rid12[i]);
		sprintf((char*)tmpbuf, "test.12b Page %d %7.1f", pid12[i], (float)pid12[i]);
		rid12f[i] = page->insertRecord(tmpbuf);
		bufMgr->unPinPage(file12ptr, pid12[i], false);
	}

	// Flush pages supposedly just written to disk
	bufMgr->flushFile(file12ptr);

	// Since dirty bit not set after second write, contents in pages not changed
	for (i = 0; i < num; i++)
	{
		bufMgr->readPage(file12ptr, pid12[i], page);
		sprintf((char*)&tmpbuf, "test.12a Page %d %7.1f", pid12[i], (float)pid12[i]);
		if(strncmp(page->getRecord(rid12[i]).c_str(), tmpbuf, strlen(tmpbuf)) != 0)
		{
			PRINT_ERROR("ERROR :: CONTENTS DID NOT MATCH");
		}
	}

	std::cout << "Test 12 passed" << std::endl;
}



