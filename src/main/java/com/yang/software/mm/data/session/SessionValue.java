package com.yang.software.mm.data.session;

public class SessionValue {
    private String userName;

    private int userId;

    private SearchCondition searchCondition = new SearchCondition();

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

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    @Override
    public String toString() {
        return "SessionValue{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", searchCondition=" + searchCondition +
                '}';
    }
}
