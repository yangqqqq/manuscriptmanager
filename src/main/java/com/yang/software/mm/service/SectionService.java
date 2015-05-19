package com.yang.software.mm.service;

import java.util.List;

import com.yang.software.mm.data.section.Section;

public interface SectionService {
    int add(Section section);

    void modify(Section section);

    Section get(int id);

    List<Section> getAll();

    void delete(int id);
}
