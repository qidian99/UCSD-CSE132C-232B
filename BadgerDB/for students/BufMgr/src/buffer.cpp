/**
 * @author See Contributors.txt for code contributors and overview of BadgerDB.
 *
 * @section LICENSE
 * Copyright (c) 2012 Database Group, Computer Sciences Department, University of Wisconsin-Madison.
 */

#include <memory>
#include <iostream>
#include "file.h"
#include "buffer.h"
#include "bufHashTbl.h"
#include "exceptions/buffer_exceeded_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "exceptions/bad_buffer_exception.h"
#include "exceptions/hash_not_found_exception.h"

namespace badgerdb
{

  BufMgr::BufMgr(std::uint32_t bufs)
      : numBufs(bufs)
  {
    bufDescTable = new BufDesc[bufs];

    for (FrameId i = 0; i < bufs; i++)
    {
      bufDescTable[i].frameNo = i;
      bufDescTable[i].valid = false;
    }

    bufPool = new Page[bufs];

    int htsize = ((((int)(bufs * 1.2)) * 2) / 2) + 1;
    hashTable = new BufHashTbl(htsize); // allocate the buffer hash table

    clockHand = bufs - 1;
  }

  BufMgr::~BufMgr()
  {

    for (int i = 0; i < numBufs; i++)
    {
      BufDesc bufDesc = bufDescTable[i];

      if (bufDesc.dirty)
      {
        bufDesc.file->writePage(bufPool[i]);
      }
    }

    delete[] bufDescTable;
    delete[] bufPool;
    // delete hashTable;
  }

  void BufMgr::advanceClock()
  {
    clockHand = (clockHand + 1) % numBufs;
  }

  void BufMgr::allocBuf(FrameId &frame)
  {

    BufDesc buf;

    int numPinned = 0;

    while (true)
    {
      // check if buffer all pinned
      if (numPinned == numBufs)
      {
        throw BufferExceededException();
      }

      advanceClock();
      buf = bufDescTable[clockHand];

      if (!buf.valid)
      {
        frame = buf.frameNo;
        break;
      }

      if (buf.refbit)
      {
        // buf.refbit = 0;
        bufDescTable[clockHand].refbit = false;
        continue;
      }

      if (buf.pinCnt != 0)
      {
        numPinned++;
        continue;
      }

      if (buf.dirty)
      {
        // flush the page back to the disk
        Page *page = &bufPool[buf.frameNo];
        buf.file->writePage(*page);
        // break;
      }

      hashTable->remove(buf.file, buf.pageNo);

      // std::cout << "ClockHand: " << clockHand << "\n";
      // bufDescTable[buf.frameNo].Clear();

      frame = buf.frameNo;
      break;
    }
  }

  void BufMgr::readPage(File *file, const PageId pageNo, Page *&page)
  {

    FrameId frameNo;
    try
    {

      hashTable->lookup(file, pageNo, frameNo);

      // std::cout << pageNo << "\n";

      bufDescTable[frameNo].pinCnt++;
      bufDescTable[frameNo].refbit = true;
      page = &bufPool[frameNo];

      // this->bufDescTable[frameNo].pinCnt++;
      // this->bufDescTable[frameNo].refbit = true;
      // page = &(this->bufPool[frameNo]);
    }
    catch (HashNotFoundException &e)
    {
      Page new_page = file->readPage(pageNo);

      allocBuf(frameNo);

      bufPool[frameNo] = new_page;
      bufDescTable[frameNo].Set(file, pageNo);

      hashTable->insert(file, pageNo, frameNo);

      page = &bufPool[frameNo];
    }
  }

  void BufMgr::unPinPage(File *file, const PageId pageNo, const bool dirty)
  {

    FrameId frameNo;

    try
    {

      hashTable->lookup(file, pageNo, frameNo);
    }
    catch (HashNotFoundException &e)
    {
      return;
    }

    BufDesc *bufDesc = &bufDescTable[frameNo];

    if (bufDesc->pinCnt == 0)
    {
      throw PageNotPinnedException(file->filename(), pageNo, frameNo);
    }

    bufDesc->pinCnt--;

    if (dirty)
    {
      bufDesc->dirty = true;
    }
  }

  void BufMgr::flushFile(const File *file)
  {
    for (int i = 0; i < numBufs; i++)
    {
      BufDesc bufDesc = bufDescTable[i];

      if (bufDesc.file == file)
      {

        if (bufDesc.pinCnt != 0)
        {
          throw PagePinnedException("Page pinned", bufDesc.pageNo, bufDesc.frameNo);
        }

        if (!bufDesc.valid)
        {
          throw BadBufferException(bufDesc.frameNo, bufDesc.dirty, bufDesc.valid, bufDesc.refbit);
        }

        if (bufDesc.dirty)
        {

          bufDesc.file->writePage(bufPool[i]);
        }

        hashTable->remove(file, bufDesc.pageNo);

        bufDescTable[i].Clear();
      }
    }
  }

  void BufMgr::allocPage(File *file, PageId &pageNo, Page *&page)
  {

    Page new_page = file->allocatePage();

    FrameId frame;
    allocBuf(frame);

    pageNo = new_page.page_number();

    // std::cout << pageNo << "\n";

    // insert into hashTable
    hashTable->insert(file, pageNo, frame);

    bufDescTable[frame].Set(file, pageNo);

    bufPool[frame] = new_page;
    page = &bufPool[frame];
  }

  void BufMgr::disposePage(File *file, const PageId PageNo)
  {
    FrameId frame;
    try
    {
      hashTable->lookup(file, PageNo, frame);

      bufDescTable[frame].Clear();
      hashTable->remove(file, PageNo);
    }
    catch (HashNotFoundException &e)
    {
    }

    file->deletePage(PageNo);
  }

  void BufMgr::printSelf(void)
  {
    BufDesc *tmpbuf;
    int validFrames = 0;

    for (std::uint32_t i = 0; i < numBufs; i++)
    {
      tmpbuf = &(bufDescTable[i]);
      std::cout << "FrameNo:" << i << " ";
      tmpbuf->Print();

      if (tmpbuf->valid == true)
        validFrames++;
    }

    std::cout << "Total Number of Valid Frames:" << validFrames << "\n";

    // std::cout << "Clock Hand: " << clockHand << "\n";
  }

}
