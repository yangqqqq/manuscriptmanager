package com.yang.software.mm.dao;

import java.util.List;

import com.yang.software.mm.data.manuscript.Manuscript;
import com.yang.software.mm.web.form.ManuscriptRecordListForm;

public interface ManuscriptDao {
    int add(Manuscript manuscript);

    void modify(Manuscript manuscript);

    Manuscript getManuscript(int id);

    List<Manuscript> getAllManuscript();

    List<ManuscriptRecordListForm> getManuscriptRecordList(int manuscriptId);

    void delete(int id);
}
