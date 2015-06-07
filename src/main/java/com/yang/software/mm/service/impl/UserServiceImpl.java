package com.yang.software.mm.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yang.software.mm.dao.UserDao;
import com.yang.software.mm.data.user.User;
import com.yang.software.mm.service.UserService;

import javax.annotation.Resource;
@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User get(int id) {
        return userDao.getUser(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> getAll() {
        return userDao.getAllUser();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(int id) {
        this.userDao.delete(id);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int add(User user) {
        return userDao.add(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void modify(User user) {
        userDao.modify(user);
    }

    public int checkUser(String name, String password) {
        List<User> userList = getAll();
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(name) && user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return -1;
    }

}
