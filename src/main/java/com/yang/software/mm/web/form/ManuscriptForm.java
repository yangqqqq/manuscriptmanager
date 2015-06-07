package com.yang.software.mm.web.form;

import java.util.Date;

import com.yang.software.mm.data.manuscript.Manuscript;
import com.yang.software.mm.data.record.Record;

public class ManuscriptForm {

    private int opType;

    private int operId;

    private int ownerId;

    private String content = "";

    private int factoryId;

    private Date opDate;

    private int lastOpId;

    private int manuscriptId;

    private int sectionId;

    private int publishTime;

    private int publishYear;

    private String count;

    private String remark;

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public int getLastOpId() {
        return lastOpId;
    }

    public void setLastOpId(int lastOpId) {
        this.lastOpId = lastOpId;
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

    public Manuscript getAddManuscript() {
        return new Manuscript(this.getOperId(), this.getOpDate());
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

    public Record getAddRecord(int manuscriptId) {
        return new Record(manuscriptId, this.operId, this.operId, this.opType,
                this.content, 0, this.opDate, this.factoryId, this.sectionId, this.publishTime, this.publishYear, this.count, this.remark);
    }

    public Record getModifyRecord(int manuscriptId) {
        return new Record(manuscriptId, this.operId, this.ownerId, this.opType,
                this.content, 0, this.opDate, this.factoryId, this.sectionId, this.publishTime, this.publishYear, this.count, this.remark);
    }
}
