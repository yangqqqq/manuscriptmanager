package com.yang.software.mm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import utils.ListUtils;

import com.yang.software.mm.dao.ManuscriptDao;
import com.yang.software.mm.dao.RecordDao;
import com.yang.software.mm.dao.SectionDao;
import com.yang.software.mm.dao.UserDao;
import com.yang.software.mm.data.manuscript.Manuscript;
import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.data.section.Section;
import com.yang.software.mm.data.session.SessionCache;
import com.yang.software.mm.data.user.User;
import com.yang.software.mm.enums.MmOpTypeEnum;
import com.yang.software.mm.service.ManuscriptService;
import com.yang.software.mm.web.form.ManuscriptForm;
import com.yang.software.mm.web.form.ManuscriptListForm;
import com.yang.software.mm.web.form.ManuscriptRecordListForm;

public class ManuscriptServiceImpl implements ManuscriptService {

    private ManuscriptDao manuscriptDao;

    private RecordDao recordDao;

    private UserDao userDao;

    private SectionDao sectionDao;

    public ManuscriptDao getManuscriptDao() {
        return manuscriptDao;
    }

    public void setManuscriptDao(ManuscriptDao manuscriptDao) {
        this.manuscriptDao = manuscriptDao;
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public SectionDao getSectionDao() {
        return sectionDao;
    }

    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(ManuscriptForm manuscriptForm) {
        Manuscript manuscript = manuscriptForm.getAddManuscript();
        int newId = manuscriptDao.add(manuscript);
        Record record = manuscriptForm.getAddRecord(newId);
        recordDao.add(record);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void modify(ManuscriptForm manuscriptForm) {
        Record latestRecord = getLatestRecord(manuscriptForm.getManuscriptId());
        Record newRecord = manuscriptForm.getAddRecord(manuscriptForm.getManuscriptId());
        newRecord.setFactoryId(latestRecord.getFactoryId());
        newRecord.setLastOpId(latestRecord.getId());
        newRecord.setRemark(latestRecord.getRemark());
        recordDao.add(newRecord);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void advice(ManuscriptForm manuscriptForm) {
        Record latestRecord = getLatestRecord(manuscriptForm.getManuscriptId());
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.ADVICE.getId());
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        newRecord.setRemark(manuscriptForm.getRemark());
        recordDao.add(newRecord);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(int id) {
        Record latestRecord = getLatestRecord(id);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.DELETE.getId());
        newRecord.setManuscriptId(id);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        newRecord.setOwnerId(latestRecord.getOwnerId());
        recordDao.add(newRecord);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void get(int id) {
        Record latestRecord = getLatestRecord(id);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.GET.getId());
        newRecord.setManuscriptId(id);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        newRecord.setOwnerId(SessionCache.getSessionValue().getUserId());
        recordDao.add(newRecord);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deliver(int manuscriptId, int toUserId) {
        Record latestRecord = getLatestRecord(manuscriptId);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.DELIVER.getId());
        newRecord.setManuscriptId(manuscriptId);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        newRecord.setOwnerId(toUserId);
        recordDao.add(newRecord);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(int manuscriptId, int factoryId) {
        Record latestRecord = getLatestRecord(manuscriptId);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.TRANSFER.getId());
        newRecord.setFactoryId(factoryId);
        newRecord.setManuscriptId(manuscriptId);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        recordDao.add(newRecord);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private List<ManuscriptListForm> getManuscriptListBase() {
        Map<Integer, Manuscript> manuscripts = this.getId2ManuscriptMap(manuscriptDao.getAllManuscript());
        List<Record> records = recordDao.getAllRecord();
        List<User> users = userDao.getAllUser();
        List<Section> sections = sectionDao.getAllSection();
        Map<Integer, ManuscriptListForm> id2ManuscriptMap = new HashMap<Integer, ManuscriptListForm>();
        List<ManuscriptListForm> result = new ArrayList<ManuscriptListForm>();
        for (Record record : records) {
            if (!id2ManuscriptMap.containsKey(record.getManuscriptId())) {
                ManuscriptListForm form = new ManuscriptListForm(record);
                form.setAutherId(manuscripts.get(record.getManuscriptId()).getUserId());
                form.setAutherName(getUserName(users, form.getAutherId()));
                form.setOwnerName(getUserName(users, form.getOwnerId()));
                form.setSectionName(getSectionName(form.getSectionId(), sections));
                id2ManuscriptMap.put(form.getManuscriptId(), form);
            } else {
                if (record.getOpDate().after(id2ManuscriptMap.get(record.getManuscriptId()).getOpDate())) {
                    ManuscriptListForm form = new ManuscriptListForm(record);
                    form.setAutherId(manuscripts.get(record.getManuscriptId()).getUserId());
                    form.setAutherName(getUserName(users, form.getAutherId()));
                    form.setOwnerName(getUserName(users, form.getOwnerId()));
                    form.setSectionName(getSectionName(form.getSectionId(), sections));
                    id2ManuscriptMap.put(form.getManuscriptId(), form);
                }
            }
        }
        result.addAll(id2ManuscriptMap.values());
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ManuscriptListForm> getManuscriptList() {
        List<ManuscriptListForm> manuscriptListForms = this.getManuscriptListBase();
        for (Iterator<ManuscriptListForm> iterator = manuscriptListForms.iterator(); iterator.hasNext(); ) {
            ManuscriptListForm manuscriptListForm = iterator.next();
            if (manuscriptListForm.getOpType() == MmOpTypeEnum.DELETE.getId()) {
                iterator.remove();
            }
        }

        return manuscriptListForms;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ManuscriptListForm> getManuscriptList(int ownerId) {
        List<ManuscriptListForm> forms = getManuscriptList();
        List<ManuscriptListForm> reslut = new ArrayList<ManuscriptListForm>();
        for (ManuscriptListForm manuscriptListForm : forms) {
            if (manuscriptListForm.getOwnerId() == ownerId) {
                reslut.add(manuscriptListForm);
            }
        }
        return reslut;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ManuscriptRecordListForm> getManuscriptRecordList(int manuscriptId) {
        return manuscriptDao.getManuscriptRecordList(manuscriptId);
    }

    public ManuscriptListForm getManuscript(int manuscriptId) {
        List<ManuscriptListForm> forms = getManuscriptListBase();
        for (ManuscriptListForm manuscriptListForm : forms) {
            if (manuscriptListForm.getManuscriptId() == manuscriptId) {
                return manuscriptListForm;
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Record getLatestRecord(int manuscriptId) {
        return recordDao.getLatestRecord(manuscriptId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Record getManuscripyRecord(int recordId) {
        return recordDao.getRecord(recordId);
    }

    public List<ManuscriptListForm> getManuscriptListOfFactory(int factoryId, int manuscriptListType) {
        List<ManuscriptListForm> forms;
        switch (manuscriptListType) {
            case 1:
                forms = getManuscriptList(SessionCache.getSessionValue().getUserId());
                break;
            case 2:
            default:
                forms = getManuscriptList();
                break;
        }
        List<ManuscriptListForm> reslut = new ArrayList<ManuscriptListForm>();
        for (ManuscriptListForm manuscriptListForm : forms) {
            if (manuscriptListForm.getFactoryId() == factoryId) {
                reslut.add(manuscriptListForm);
            }
        }
        return reslut;
    }

    public List<ManuscriptListForm> getManuscriptRecyclerList() {
        List<ManuscriptListForm> manuscriptList = this.getManuscriptListBase();
        for (Iterator<ManuscriptListForm> iterator = manuscriptList.iterator(); iterator.hasNext(); ) {
            ManuscriptListForm manuscriptListForm = iterator.next();
            if (manuscriptListForm.getOpType() != MmOpTypeEnum.DELETE.getId()) {
                iterator.remove();
            }
        }
        return manuscriptList;
    }

    public void period(int manuscriptId, int periodId) {
        Record latestRecord = getLatestRecord(manuscriptId);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.PERIOD.getId());
        newRecord.setPublishTime(periodId);
        newRecord.setManuscriptId(manuscriptId);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        recordDao.add(newRecord);
    }

    private String getSectionName(int sectionId, List<Section> sections) {
        for (Section section : sections) {
            if (sectionId == section.getId()) {
                return section.getSectionName();
            }
        }
        return "未分组栏目";
    }

    private String getUserName(List<User> users, int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user.getName();
            }
        }
        return "";
    }


    private Map<Integer, Manuscript> getId2ManuscriptMap(List<Manuscript> manuscripts) {
        Map<Integer, Manuscript> map = new HashMap<Integer, Manuscript>();
        for (Manuscript manuscript : manuscripts) {
            map.put(manuscript.getId(), manuscript);
        }

        return map;
    }
}
