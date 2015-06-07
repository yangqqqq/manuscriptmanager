package com.yang.software.mm.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yang.software.mm.dao.SectionDao;
import com.yang.software.mm.data.section.Section;
import com.yang.software.mm.service.SectionService;

import javax.annotation.Resource;
@Component("sectionService")
@Transactional
public class SectionServiceImpl implements SectionService {
    @Resource(name = "sectionDao")
    private SectionDao sectionDao;

    public SectionDao getSectionDao() {
        return sectionDao;
    }

    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int add(Section section) {
        return sectionDao.add(section);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void modify(Section section) {
        sectionDao.modify(section);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Section get(int id) {
        return sectionDao.getSection(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Section> getAll() {
        return sectionDao.getAllSection();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(int id) {
        sectionDao.delete(id);
    }

}
