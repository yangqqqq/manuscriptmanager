package com.yang.software.mm.web.form;

import java.util.List;

import com.yang.software.mm.data.user.User;

public class ManuscriptDeliverForm {
    private List<User> otherUsers;

    private int deliverUserId;

    public List<User> getOtherUsers() {
        return otherUsers;
    }

    public void setOtherUsers(List<User> otherUsers) {
        this.otherUsers = otherUsers;
    }

    public int getDeliverUserId() {
        return deliverUserId;
    }

    public void setDeliverUserId(int deliverUserId) {
        this.deliverUserId = deliverUserId;
    }
}
