package com.yang.software.mm.dao.impl;

import java.math.BigInteger;
import java.util.List;

import com.yang.software.mm.data.Constants;
import com.yang.software.mm.data.session.SearchCondition;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.ManuscriptDao;
import com.yang.software.mm.data.manuscript.Manuscript;
import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.web.form.ManuscriptRecordListForm;
import utils.StringUtils;

import javax.annotation.Resource;
@Component("manuscriptDao")
public class ManuscriptDaoImpl implements ManuscriptDao {
    @Resource(name = "sessionFactory")
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
        return (Manuscript) getSession().load(Manuscript.class, id);
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

    @Override
    public List<Manuscript> getManuscripts(SearchCondition condition) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" where 1 = 1");
        if (!Constants.NOT_INIT_NUMBER_STR.equals(condition.getSection()))
        {
            stringBuilder.append(" and manuscript.sectionId = ").append(condition.getSection());
        }
        if (!Constants.NOT_INIT_NUMBER_STR.equals(condition.getPublishTime()))
        {
            stringBuilder.append(" and manuscript.publishTime = ").append(condition.getPublishTime());
        }
        if (!Constants.NOT_INIT_NUMBER_STR.equals(condition.getPublishYear()))
        {
            stringBuilder.append(" and manuscript.publishYear = ").append(condition.getPublishYear());
        }
        if (!Constants.NOT_INIT_NUMBER_STR.equals(condition.getOwnerId()))
        {
            stringBuilder.append(" and manuscript.ownerid = ").append(condition.getOwnerId());
        }
        if (!Constants.NOT_INIT_NUMBER_STR.equals(condition.getManuscriptId()))
        {
            stringBuilder.append(" and manuscript.id = ").append(condition.getManuscriptId());
        }
        if (StringUtils.hasText(condition.getContent()))
        {
            stringBuilder.append(" and manuscript.content like '%" + condition.getContent() + "%' ");
        }
        if (condition.getFactoryIds() != null)
        {
            stringBuilder.append(" and manuscript.factoryId in ").append(StringUtils.getInsql(condition.getFactoryIds()));
        }

        String conditions = stringBuilder.toString();
        String sql = "select * from manuscript" + conditions + " order by manuscript.opdate desc";
        if (Constants.page.get() != null)
        {
            if (Constants.page.get().getCurrentPage() == 0)
            {
                sql += " limit " + Constants.page.get().getCurrentPage() * 100 + ", " + 100;
            }
            else
            {
                SQLQuery query=this.getSession().createSQLQuery("select count(id) as number from manuscript");
                List<BigInteger> list = query.list();
                int num = list.get(0).intValue();
                sql += " limit 100 , " + num;
            }
            Constants.page.remove();
        }
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(Manuscript.class);

        return query.list();
    }
}
