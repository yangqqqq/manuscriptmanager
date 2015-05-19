package com.yang.software.mm.web.form;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import utils.StringUtils;

import com.yang.software.mm.enums.FactoryTypeEnum;
import com.yang.software.mm.enums.MmOpTypeEnum;
import com.yang.software.mm.enums.PublishTimeEnum;

@Entity
public class ManuscriptRecordListForm {
    @Id
    private int recordId;

    private Date opDate;

    private int opType;

    private int opErId;

    private String opErName;

    private int factoryId;

    private String content;

    private String count;

    private String sectionName;

    private int publishTime;

    private int publishYear;

    private String remark;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public int getOpErId() {
        return opErId;
    }

    public void setOpErId(int opErId) {
        this.opErId = opErId;
    }

    public String getOpErName() {
        return opErName;
    }

    public void setOpErName(String opErName) {
        this.opErName = opErName;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryDescription() {
        return FactoryTypeEnum.getDescription(factoryId);
    }

    public String getOpTypeDescription() {
        return MmOpTypeEnum.getOpTypeDescription(opType);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSummary() {
        return StringUtils.getSummary(this.content);
    }

    public int getContentLength() {
        return content.length();
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
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

    public String getPublishTimeDescription() {
        return PublishTimeEnum.getPublicTimeDescription(publishTime);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
