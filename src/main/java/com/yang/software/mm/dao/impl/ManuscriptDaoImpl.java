package com.yang.software.mm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.ManuscriptDao;
import com.yang.software.mm.data.manuscript.Manuscript;
import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.web.form.ManuscriptRecordListForm;

public class ManuscriptDaoImpl implements ManuscriptDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get the current Session.
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public StatelessSession getStatelsesSession() {
        return sessionFactory.openStatelessSession();
    }

    public Manuscript getManuscript(int id) {
        Assert.notNull(id, "id can not be empty");
        return (Manuscript) getSession().load(Manuscript.class, (long) id);
    }

    @SuppressWarnings("unchecked")
    public List<Manuscript> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(Manuscript.class);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    public List<Manuscript> getAllManuscript() {
        return find();
    }

    public List<ManuscriptRecordListForm> getManuscriptRecordList(int manuscriptId) {
        String sql = "select "
                + "record.*,record.id recordId, section.sectionName sectionName,user.name opErName "
                + "from user, record, section "
                + "where user.id = record.operid "
                + "and record.sectionId = section.id "
                + "and record.manuscriptId = " + manuscriptId + " "
                + "order by record.opdate";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(ManuscriptRecordListForm.class);

        List<ManuscriptRecordListForm> result = query.list();
        return result;
    }

    public void delete(int id) {
        getSession().delete(getManuscript(id));
    }

    public int add(Manuscript manuscript) {
        Assert.notNull(manuscript, "entity Can not be empty");
        return (Integer) getSession().save(manuscript);
    }

    public void modify(Manuscript manuscript) {
        Assert.notNull(manuscript, "entity Can not be empty");
        getSession().update(manuscript);
    }

}
