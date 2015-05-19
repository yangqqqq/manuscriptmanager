package com.yang.software.mm.dao;

import java.util.List;

import com.yang.software.mm.data.user.User;

public interface UserDao {
    int add(User user);

    void modify(User user);

    User getUser(int id);

    List<User> getAllUser();

    void delete(int id);
}
