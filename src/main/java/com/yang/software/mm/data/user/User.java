package com.yang.software.mm.data.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yang.software.mm.enums.RoleEnum;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String password;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password
                + ", roleId=" + roleId + "]";
    }

    public User() {
        super();
    }

    public User(int id, String name, String password, int roleId) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return RoleEnum.getRoleDescription(this.roleId);
    }


}
