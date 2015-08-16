package com.yang.software.mm.data.manuscript;

import com.yang.software.mm.data.record.Record;

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

    private int operId;

    private int ownerId;

    private int opType;

    private String content;

    private int lastOpId;

    private Date opDate;

    private int factoryId;

    private int sectionId;

    public int publishTime;

    public int publishYear;

    private String count;

    private String remark;

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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLastOpId() {
        return lastOpId;
    }

    public void setLastOpId(int lastOpId) {
        this.lastOpId = lastOpId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOperId() {

        return operId;
    }

    public void setOperId(int operId) {
        this.operId = operId;
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
        this.content = "";
        this.remark = "";
        this.opDate = date;
        this.count = "";
    }

    public Manuscript() {
        super();
    }


    public void setRecord(Record record) {
        this.operId = record.getOperId();
        this.ownerId = record.getOwnerId();
        this.opType = record.getOpType();
        this.content = record.getContent();
        this.lastOpId = record.getLastOpId();
        this.opDate = record.getOpDate();
        this.factoryId = record.getFactoryId();
        this.sectionId = record.getSectionId();
        this.publishTime = record.getPublishTime();
        this.publishYear = record.getPublishYear();
        this.count = record.getCount();
        this.remark = record.getRemark();
    }
}
