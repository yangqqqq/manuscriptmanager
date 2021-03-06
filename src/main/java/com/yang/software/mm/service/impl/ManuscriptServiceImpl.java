package com.yang.software.mm.service.impl;

import java.util.*;

import com.yang.software.mm.data.Constants;
import com.yang.software.mm.data.session.SearchCondition;
import org.springframework.stereotype.Component;
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

import javax.annotation.Resource;

@Component("manuscriptService")
@Transactional
public class ManuscriptServiceImpl implements ManuscriptService {
    @Resource(name = "manuscriptDao")
    private ManuscriptDao manuscriptDao;
    @Resource(name = "recordDao")
    private RecordDao recordDao;
    @Resource(name = "userDao")
    private UserDao userDao;
    @Resource(name = "sectionDao")
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
        manuscript.setRecord(record);
        manuscriptDao.modify(manuscript);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void modify(ManuscriptForm manuscriptForm) {
        Record latestRecord = getLatestRecord(manuscriptForm.getManuscriptId());
        Record newRecord = manuscriptForm.getModifyRecord(manuscriptForm.getManuscriptId());
        newRecord.setLastOpId(latestRecord.getId());
        newRecord.setRemark(latestRecord.getRemark());
        recordDao.add(newRecord);
        Manuscript manuscript = manuscriptDao.getManuscript(manuscriptForm.getManuscriptId());
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
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
        Manuscript manuscript = manuscriptDao.getManuscript(manuscriptForm.getManuscriptId());
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
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
        Manuscript manuscript = manuscriptDao.getManuscript(id);
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void realDelete(int id) {
        List<ManuscriptRecordListForm> list = manuscriptDao.getManuscriptRecordList(id);
        for (ManuscriptRecordListForm m : list)
        {
            recordDao.delete(m.getRecordId());
        }
        manuscriptDao.delete(id);
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
        Manuscript manuscript = manuscriptDao.getManuscript(id);
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
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
        Manuscript manuscript = manuscriptDao.getManuscript(manuscriptId);
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
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
        Manuscript manuscript = manuscriptDao.getManuscript(manuscriptId);
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private List<ManuscriptListForm> getManuscriptListBase() {
        Map<Integer, Manuscript> manuscripts = this.getId2ManuscriptMap(manuscriptDao.getAllManuscript());
        List<Record> records = recordDao.getAllRecord();
        List<User> users = userDao.getAllUser();
        List<Section> sections = sectionDao.getAllSection();
        List<ManuscriptListForm> result = new ArrayList<ManuscriptListForm>();
        for (Record record : records) {
            ManuscriptListForm form = new ManuscriptListForm(record);
            form.setAutherId(manuscripts.get(record.getManuscriptId()).getUserId());
            form.setAutherName(getUserName(users, form.getAutherId()));
            form.setOwnerName(getUserName(users, form.getOwnerId()));
            form.setSectionName(getSectionName(form.getSectionId(), sections));
            result.add(form);
        }
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
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setManuscriptId(String.valueOf(manuscriptId));
        searchCondition.setFactoryIds(null);
        searchCondition.setPublishYear(Constants.NOT_INIT_NUMBER_STR);
        List<ManuscriptListForm> forms = this.getManuscriptList(searchCondition);
        if (!forms.isEmpty())
        {
            return forms.get(0);
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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void period(int manuscriptId, int periodId) {
        Record latestRecord = getLatestRecord(manuscriptId);
        Record newRecord = latestRecord.getOpCopy();
        newRecord.setOpType(MmOpTypeEnum.PERIOD.getId());
        newRecord.setPublishTime(periodId);
        newRecord.setManuscriptId(manuscriptId);
        newRecord.setOpDate(new Date());
        newRecord.setOperId(SessionCache.getSessionValue().getUserId());
        recordDao.add(newRecord);
        Manuscript manuscript = manuscriptDao.getManuscript(manuscriptId);
        manuscript.setRecord(newRecord);
        manuscriptDao.modify(manuscript);
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

    @Override
    public List<ManuscriptListForm> getManuscriptList(SearchCondition searchCondition) {
        List<Manuscript> manuscripts = manuscriptDao.getManuscripts(searchCondition);
        List<User> users = userDao.getAllUser();
        List<Section> sections = sectionDao.getAllSection();
        List<ManuscriptListForm> result = new ArrayList<ManuscriptListForm>();
        for (Manuscript manuscript : manuscripts) {
            ManuscriptListForm form = new ManuscriptListForm(manuscript);
            form.setAutherId(manuscript.getUserId());
            form.setAutherName(getUserName(users, form.getAutherId()));
            form.setOwnerName(getUserName(users, form.getOwnerId()));
            form.setSectionName(getSectionName(form.getSectionId(), sections));
            form.setCreateDate(manuscript.getDate());
            result.add(form);
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void resetManuscript() {
        List<Record> records = recordDao.getRecords(Record.getSearchCondition());
        List<Manuscript> manuscripts = manuscriptDao.getAllManuscript();
        Map<Integer, Record> map = new HashMap<Integer, Record>();
        for (Record record : records)
        {
            map.put(record.getManuscriptId(), record);
        }
        for (Manuscript manuscript: manuscripts)
        {

            Record old = map.get(manuscript.getId());
            if (old != null) {
                manuscript.setRecord(old);
                manuscriptDao.modify(manuscript);
            }
        }
    }
}
