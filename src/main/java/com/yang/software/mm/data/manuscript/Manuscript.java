package com.yang.software.mm.data.manuscript;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MANUSCRIPT")
public class Manuscript {
    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Manusript [id=" + id + ", userId=" + userId + ", date=" + date
                + "]";
    }

    public Manuscript(int userId, Date date) {
        super();
        this.userId = userId;
        this.date = date;
    }

    public Manuscript() {
        super();
    }
}
