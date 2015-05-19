package com.yang.software.mm.data.session;

public class SessionValue {
    private String userName;

    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SessionValue [userName=" + userName + ", userId=" + userId
                + "]";
    }

}
