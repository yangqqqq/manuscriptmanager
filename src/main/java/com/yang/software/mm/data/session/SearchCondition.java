package com.yang.software.mm.data.session;

import com.yang.software.mm.data.Constants;
import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.enums.FactoryTypeEnum;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yang on 2015/5/20.
 */
public class SearchCondition {
    /**按内容查询*/
    private String content = "";
    /**按栏目查询*/
    private String section = Constants.NOT_INIT_NUMBER_STR;
    /**期数*/
    private String publishTime = Constants.NOT_INIT_NUMBER_STR;
    /**年份*/
    private String publishYear = (new Date()).getYear() + 1900 + "";
    /**所有者*/
    private String ownerId = Constants.NOT_INIT_NUMBER_STR;
    /**所在库*/
    private String[] factoryIds = new String[]{String.valueOf(FactoryTypeEnum.EDIT_ID), String.valueOf(FactoryTypeEnum.WAIT_PUBLISH_ID), String.valueOf(FactoryTypeEnum.WAIT_PUBLISH_ID)};
    private boolean isRecyle = false;

    public SearchCondition() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String[] getFactoryIds() {
        return factoryIds;
    }

    public void setFactoryIds(String[] factoryIds) {
        this.factoryIds = factoryIds;
    }

    public boolean isRecyle() {
        return isRecyle;
    }

    public void setRecyle(boolean isRecyle) {
        this.isRecyle = isRecyle;
    }

    public Set<Integer> getFactoryIdSet()
    {
        Set<Integer> factoryIdSet = new HashSet<Integer>();
        for (String id : getFactoryIds())
        {
            factoryIdSet.add(Integer.valueOf(id));
        }
        return factoryIdSet;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "content='" + content + '\'' +
                ", section='" + section + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }

    public Record getSearchRecordCondtion()
    {
        Record record = Record.getSearchCondition();
        record.setContent(this.content);
        record.setSectionId(Integer.valueOf(this.section));
        record.setPublishTime(Integer.valueOf(this.publishTime));
        record.setPublishYear(Integer.valueOf(this.publishYear));
        record.setOwnerId(Integer.valueOf(this.ownerId));

        return record;
    }
}
