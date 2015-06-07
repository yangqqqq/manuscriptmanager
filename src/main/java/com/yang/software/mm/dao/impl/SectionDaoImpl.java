package com.yang.software.mm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.SectionDao;
import com.yang.software.mm.data.section.Section;

import javax.annotation.Resource;

@Component("sectionDao")
public class SectionDaoImpl implements SectionDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int add(Section section) {
        Assert.notNull(section, "entity Can not be empty");
        return (Integer) getSession().save(section);
    }

    public void modify(Section section) {
        Assert.notNull(section, "entity Can not be empty");
        getSession().update(section);
    }

    public void save(Section section) {
        Assert.notNull(section, "entity Can not be empty");
        getSession().saveOrUpdate(section);
    }

    public Section getSection(int id) {
        Assert.notNull(id, "id can not be empty");
        return (Section) getSession().load(Section.class, id);
    }

    public List<Section> getAllSection() {
        return find();
    }

    public void delete(int id) {
        getSession().delete(this.getSection(id));
    }

    @SuppressWarnings("unchecked")
    public List<Section> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(Section.class);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * Get the current Session.
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
