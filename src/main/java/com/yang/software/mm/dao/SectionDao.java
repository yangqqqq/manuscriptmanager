package com.yang.software.mm.dao;

import java.util.List;

import com.yang.software.mm.data.section.Section;

public interface SectionDao {
    int add(Section section);

    void modify(Section section);

    Section getSection(int id);

    List<Section> getAllSection();

    void delete(int id);
}
