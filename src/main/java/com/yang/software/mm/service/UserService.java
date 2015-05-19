package com.yang.software.mm.service;

import java.util.List;

import com.yang.software.mm.data.user.User;

public interface UserService {
    int add(User user);

    void modify(User user);

    User get(int id);

    List<User> getAll();

    void delete(int id);

    int checkUser(String name, String password);
}
