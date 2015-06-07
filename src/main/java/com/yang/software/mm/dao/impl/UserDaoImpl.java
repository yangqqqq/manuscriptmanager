package com.yang.software.mm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yang.software.mm.dao.UserDao;
import com.yang.software.mm.data.user.User;

import javax.annotation.Resource;

@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Assert.notNull(user, "entity Can not be empty");
        getSession().saveOrUpdate(user);
    }

    public User getUser(int id) {
        Assert.notNull(id, "id can not be empty");
        return (User) getSession().load(User.class, id);
    }

    public List<User> getAllUser() {
        return find();
    }

    @SuppressWarnings("unchecked")
    public List<User> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(User.class);
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

    public StatelessSession getStatelsesSession() {
        return sessionFactory.openStatelessSession();
    }

    public void delete(int id) {
        getSession().delete(this.getUser(id));
    }

    public int add(User user) {
        Assert.notNull(user, "entity Can not be empty");
        return (Integer) getSession().save(user);
    }

    public void modify(User user) {
        Assert.notNull(user, "entity Can not be empty");
        getSession().update(user);

    }

}
