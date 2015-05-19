package com.yang.software.mm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.RecordDao;
import com.yang.software.mm.data.record.Record;

public class RecordDaoImpl implements RecordDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int add(Record record) {
        Assert.notNull(record, "entity Can not be empty");
        return (Integer) getSession().save(record);
    }

    public void modify(Record record) {
        Assert.notNull(record, "entity Can not be empty");
        getSession().update(record);
    }

    public Record getRecord(int id) {
        Assert.notNull(id, "id can not be empty");
        Record result = (Record) getSession().load(Record.class, id);
        return result;
    }

    public List<Record> getAllRecord() {
        return find();
    }

    public void delete(int id) {
        getSession().delete(getRecord(id));
    }


    @SuppressWarnings("unchecked")
    public List<Record> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(Record.class);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    public Record getLatestRecord(int manuId) {
        String sql = "select * "
                + "from record "
                + "where record.manuscriptId = " + manuId + " "
                + "order by record.opdate desc limit 1";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(Record.class);

        List<Record> result = query.list();
        return result.get(0);
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

}
