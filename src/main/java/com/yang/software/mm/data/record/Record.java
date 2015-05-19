package com.yang.software.mm.data.record;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECORD")
public class Record {
    @Id
    @GeneratedValue
    private int id;

    private int manuscriptId;

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

    public int getOperId() {
        return operId;
    }

    public void setOperId(int operId) {
        this.operId = operId;
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

    public int getManuscriptId() {
        return manuscriptId;
    }

    public void setManuscriptId(int manuscriptId) {
        this.manuscriptId = manuscriptId;
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

    @Override
    public String toString() {
        return "Record [id=" + id + ", manuscriptId=" + manuscriptId
                + ", operId=" + operId + ", ownerId=" + ownerId + ", opType="
                + opType + ", content=" + content + ", lastOpId=" + lastOpId
                + ", opDate=" + opDate + ", factoryId=" + factoryId
                + ", sectionId=" + sectionId + ", publishTime=" + publishTime
                + ", publishYear=" + publishYear + ", count=" + count
                + ", remark=" + remark + "]";
    }

    public Record(int manuscriptId, int operId, int ownerId, int opType,
                  String content, int lastOpId, Date opDate, int factoryId,
                  int sectionId, int publishTime, int publishYear, String count, String remark) {
        super();
        this.manuscriptId = manuscriptId;
        this.operId = operId;
        this.ownerId = ownerId;
        this.opType = opType;
        this.content = content;
        this.lastOpId = lastOpId;
        this.opDate = opDate;
        this.factoryId = factoryId;
        this.sectionId = sectionId;
        this.publishTime = publishTime;
        this.publishYear = publishYear;
        this.count = count;
        this.remark = remark;
    }

    public Record() {
        super();
    }

    public Record getOpCopy() {
        return new Record(manuscriptId, operId, ownerId, opType, content, id,
                opDate, factoryId, sectionId, publishTime, publishYear, count, remark);
    }
}
