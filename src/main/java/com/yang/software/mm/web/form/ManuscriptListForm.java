package com.yang.software.mm.web.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.yang.software.mm.data.manuscript.Manuscript;
import utils.StringUtils;

import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.enums.FactoryTypeEnum;
import com.yang.software.mm.enums.PublishTimeEnum;

@Entity
public class ManuscriptListForm {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Id
    private int id;

    private int manuscriptId;

    private Date opDate;

    private int ownerId;

    private String ownerName;

    private int autherId;

    private String autherName;

    private String content;

    private int factoryId;

    private int sectionId;

    private String sectionName;

    private int publishTime;

    private int publishYear;

    private String count;

    private int opType;

    private String remark;

    private Date createDate;

    public String getSummary() {
        return StringUtils.getFirstStr(StringUtils.getSummary(content), 40 - remark.length());
    }

    public Date getOpDate() {
        return opDate;
    }

    public String getOpDateStr()
    {
        return sdf.format(this.opDate);
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getAutherId() {
        return autherId;
    }

    public void setAutherId(int autherId) {
        this.autherId = autherId;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public int getManuscriptId() {
        return manuscriptId;
    }

    public void setManuscriptId(int manuscriptId) {
        this.manuscriptId = manuscriptId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentLength() {
        return content.length();
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

    public String getPublishTimeDescription() {
        return PublishTimeEnum.getPublicTimeDescription(publishTime);
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
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

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountDisplay() {
        return StringUtils.getFirstStr(count, 8);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getCreateDateStr()
    {
        return sdf.format(createDate);
    }
    public ManuscriptListForm(Record record) {
        super();
        this.id = record.getId();
        this.manuscriptId = record.getManuscriptId();
        this.opDate = record.getOpDate();
        this.ownerId = record.getOwnerId();
        this.content = record.getContent();
        this.factoryId = record.getFactoryId();
        this.sectionId = record.getSectionId();
        this.publishTime = record.getPublishTime();
        this.publishYear = record.getPublishYear();
        this.count = record.getCount();
        this.opType = record.getOpType();
        this.remark = record.getRemark();
    }


    public ManuscriptListForm(Manuscript manuscript) {
        super();
        this.id = manuscript.getId();
        this.manuscriptId = manuscript.getId();
        this.opDate = manuscript.getOpDate();
        this.ownerId = manuscript.getOwnerId();
        this.content = manuscript.getContent();
        this.factoryId = manuscript.getFactoryId();
        this.sectionId = manuscript.getSectionId();
        this.publishTime = manuscript.getPublishTime();
        this.publishYear = manuscript.getPublishYear();
        this.count = manuscript.getCount();
        this.opType = manuscript.getOpType();
        this.remark = manuscript.getRemark();
    }

    public ManuscriptListForm() {
        super();
    }
}

