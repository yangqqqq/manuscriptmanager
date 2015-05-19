package com.yang.software.mm.dao;

import java.util.List;

import com.yang.software.mm.data.record.Record;

public interface RecordDao {
    int add(Record record);

    void modify(Record record);

    Record getRecord(int id);

    List<Record> getAllRecord();

    void delete(int id);

    Record getLatestRecord(int manuId);
}
