package com.yang.software.mm.dao.impl;

import java.util.List;

import com.yang.software.mm.data.Constants;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.RecordDao;
import com.yang.software.mm.data.record.Record;
import utils.StringUtils;

import javax.annotation.Resource;

@Component("recordDao")
public class RecordDaoImpl implements RecordDao {
    @Resource(name = "sessionFactory")
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
        String sql = "select record.*, t.maxId from(select *, max(id) maxId from record group by manuscriptId) t, record where t.maxId = record.id";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(Record.class);

        return query.list();
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

    @Override
    public List<Record> getRecords(Record condition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" where 1 = 1");
        if (Constants.NOT_INIT_NUMBER != condition.getSectionId())
        {
            stringBuilder.append(" and record.sectionId = ").append(condition.getSectionId());
        }
        if (Constants.NOT_INIT_NUMBER != condition.getPublishTime())
        {
            stringBuilder.append(" and record.publishTime = ").append(condition.getPublishTime());
        }
        if (Constants.NOT_INIT_NUMBER != condition.getPublishYear())
        {
            stringBuilder.append(" and record.publishYear = ").append(condition.getPublishYear());
        }
        if (Constants.NOT_INIT_NUMBER != condition.getOwnerId())
        {
            stringBuilder.append(" and record.ownerid = ").append(condition.getOwnerId());
        }
        if (Constants.NOT_INIT_NUMBER != condition.getManuscriptId())
        {
            stringBuilder.append(" and record.manuscriptId = ").append(condition.getManuscriptId());
        }
        if (StringUtils.hasText(condition.getContent()))
        {
            stringBuilder.append(" and record.content like '%" + condition.getContent() + "%' ");
        }

        String conditions = stringBuilder.toString();
        String sql = "select record.*, t.maxId from(select max(id) maxId from record group by manuscriptId) t, (select * from record " + conditions + ")record where t.maxId = record.id";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(Record.class);

        return query.list();
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
