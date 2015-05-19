package com.yang.software.mm.service;

import java.util.List;

import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.web.form.ManuscriptForm;
import com.yang.software.mm.web.form.ManuscriptListForm;
import com.yang.software.mm.web.form.ManuscriptRecordListForm;

public interface ManuscriptService {
    void add(ManuscriptForm manuscriptForm);

    void modify(ManuscriptForm manuscriptForm);

    void advice(ManuscriptForm manuscriptForm);

    void delete(int id);

    void get(int id);

    void deliver(int manuscriptId, int toUserId);

    void transfer(int manuscriptId, int factoryId);

    void period(int manuscriptId, int periodId);

    List<ManuscriptListForm> getManuscriptList();

    List<ManuscriptListForm> getManuscriptList(int ownerId);

    ManuscriptListForm getManuscript(int manuscriptId);

    List<ManuscriptRecordListForm> getManuscriptRecordList(int manuscriptId);

    Record getManuscripyRecord(int recordId);

    List<ManuscriptListForm> getManuscriptListOfFactory(int factoryId, int manuscriptListType);

    List<ManuscriptListForm> getManuscriptRecyclerList();
}
