package com.yang.software.mm.web.controller;

import com.yang.software.mm.data.Constants;
import com.yang.software.mm.data.record.Record;
import com.yang.software.mm.data.section.Section;
import com.yang.software.mm.data.session.SessionCache;
import com.yang.software.mm.data.session.SessionCacheKey;
import com.yang.software.mm.data.user.User;
import com.yang.software.mm.enums.FactoryTypeEnum;
import com.yang.software.mm.enums.MmOpTypeEnum;
import com.yang.software.mm.enums.RoleEnum;
import com.yang.software.mm.frame.Page;
import com.yang.software.mm.service.ManuscriptService;
import com.yang.software.mm.service.SectionService;
import com.yang.software.mm.service.UserService;
import com.yang.software.mm.web.form.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class ManuscriptController{
    @Resource(name = "manuscriptService")
    ManuscriptService manuscriptService;
    @Resource(name = "userService")
    UserService userService;
    @Resource(name = "sectionService")
    SectionService sectionService;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");

    public ManuscriptService getManuscriptService() {
        return manuscriptService;
    }

    public void setManuscriptService(ManuscriptService manuscriptService) {
        this.manuscriptService = manuscriptService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SectionService getSectionService() {
        return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "manuscriptAddInit")
    public ModelAndView manuscriptAddInit(HttpServletRequest request,
                                          HttpServletResponse response, Object command) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Section> sections = sectionService.getAll();
        User user = userService.get(SessionCache.getSessionValue().getUserId());
        FactoryTypeEnum[] factorys = RoleEnum.getFactoryTypeEnum(user.getRoleId());
        result.put("sections", sections);
        result.put("factorys", factorys);
        return new ModelAndView("../../jsp/manuscript/ManuscriptAdd", result);
    }

    @RequestMapping(value = "manuscriptEditInit")
    public ModelAndView manuscriptEditInit(HttpServletRequest request,
                                           HttpServletResponse response, Object command) {
        String manuscriptId = request.getParameter("manuscriptId");
        if (!StringUtils.hasText(manuscriptId)) {
            return new ModelAndView("../../jsp/manuscript/ManuscriptMainPreview");
        }
        List<Section> sections = sectionService.getAll();
        ManuscriptListForm form = manuscriptService.getManuscript(Integer.valueOf(manuscriptId));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("command", form);
        result.put("sections", sections);
        User user = userService.get(SessionCache.getSessionValue().getUserId());
        FactoryTypeEnum[] factorys = RoleEnum.getFactoryTypeEnum(user.getRoleId());
        result.put("factorys", factorys);
        List<User> users = userService.getAll();
        for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
            User u = (User) iterator.next();
            if (u.getName().equals("admin")) {
                iterator.remove();
            }
        }
        result.put("users", users);
        return new ModelAndView("../../jsp/manuscript/ManuscriptEdit", result);
    }

    @RequestMapping(value = "manuscriptEdit")
    public ModelAndView manuscriptEdit(HttpServletRequest request,
                                       HttpServletResponse response, ManuscriptForm command) {
        command.setOpDate(new Date());
        command.setOperId(SessionCache.getSessionValue().getUserId());
        command.setOpType(MmOpTypeEnum.MODIFY.getId());
        manuscriptService.modify(command);
        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptAdd")
    public ModelAndView manuscriptAdd(HttpServletRequest request,
                                      HttpServletResponse response, ManuscriptForm command) {
        command.setOpDate(new Date());
        command.setLastOpId(0);
        command.setOperId(SessionCache.getSessionValue().getUserId());
        command.setOwnerId(SessionCache.getSessionValue().getUserId());
        command.setOpType(MmOpTypeEnum.ADD.getId());

        manuscriptService.add(command);
        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptMainPreview")
    public ModelAndView manuscriptMainPreview(HttpServletRequest request,
                                              HttpServletResponse response) {
        String manuscriptId = request.getParameter("manuscriptId");
        if (!StringUtils.hasText(manuscriptId)) {
            return new ModelAndView("../../jsp/manuscript/ManuscriptMainPreview");
        }
        ManuscriptListForm form = manuscriptService.getManuscript(Integer.valueOf(manuscriptId));
        return new ModelAndView("../../jsp/manuscript/ManuscriptMainPreview", "content", form.getContent());
    }

    @RequestMapping(value = "manuscriptRecordPreview")
    public ModelAndView manuscriptRecordPreview(HttpServletRequest request,
                                                HttpServletResponse response) {
        String recordId = request.getParameter("recordId");
        if (!StringUtils.hasText(recordId)) {
            return new ModelAndView("../../jsp/manuscript/ManuscriptMainPreview");
        }
        Record result = manuscriptService.getManuscripyRecord(Integer.valueOf(recordId));
        return new ModelAndView("../../jsp/manuscript/ManuscriptMainPreview", "content", result.getContent());
    }

    @RequestMapping(value = "manuscriptOpInit")
    public ModelAndView manuscriptOpInit(HttpServletRequest request,
                                         HttpServletResponse response, ManuscriptOpForm form) {
        MmOpTypeEnum opTypeEnum = MmOpTypeEnum.get(Integer.valueOf(form.getOpType()));

        switch (opTypeEnum) {
            case DELETE:
                deleteManuscript(form.getSelectId());
                return new ModelAndView("redirect:/manuscriptList");
            case GET:
                getManuscript(form.getSelectId());
                return new ModelAndView("redirect:/manuscriptList");
            case DELIVER:
                SessionCache.put(SessionCacheKey.DELIVER_MANUSCRIPT_ID, form.getSelectId());
                return new ModelAndView("redirect:/manuscriptDeliverInit");
            case TRANSFER:
                SessionCache.put(SessionCacheKey.DELIVER_MANUSCRIPT_ID, form.getSelectId());
                return new ModelAndView("redirect:/manuscriptTransferInit");
            case PERIOD:
                SessionCache.put(SessionCacheKey.DELIVER_MANUSCRIPT_ID, form.getSelectId());
                return new ModelAndView("redirect:/manuscriptPeriodInit");
            default:
                break;
        }
        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptDelete")
    public ModelAndView manuscriptDelete(HttpServletRequest request,
                                         HttpServletResponse response, ManuscriptOpForm form) {
        realDeleteManuscript(form.getSelectId());

        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptDeliverInit")
    public ModelAndView manuscriptDeliverInit(HttpServletRequest request,
                                              HttpServletResponse response) {
        List<User> users = userService.getAll();
        for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            if (user.getId() == SessionCache.getSessionValue().getUserId()
                    || user.getName().equals("admin")) {
                iterator.remove();
            }
        }
        return new ModelAndView("../../jsp/manuscript/ManuscriptDeliver", "command", users);
    }

    @RequestMapping(value = "manuscriptDeliver")
    public ModelAndView manuscriptDeliver(HttpServletRequest request,
                                          HttpServletResponse response, ManuscriptDeliverForm form) {
        String[] manuscriptIds = (String[]) SessionCache.get(SessionCacheKey.DELIVER_MANUSCRIPT_ID);
        for (String id : manuscriptIds) {
            manuscriptService.deliver(Integer.valueOf(id), form.getDeliverUserId());
        }

        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptAdviceInit")
    public ModelAndView manuscriptAdviceInit(HttpServletRequest request,
                                             HttpServletResponse response) {

        String manuscriptId = request.getParameter("manuscriptId");
        ManuscriptListForm form = manuscriptService.getManuscript(Integer.valueOf(manuscriptId));
        return new ModelAndView("../../jsp/manuscript/ManuscriptAdvice", "command", form);
    }

    @RequestMapping(value = "manuscriptAdvice")
    public ModelAndView manuscriptAdvice(HttpServletRequest request,
                                         HttpServletResponse response) {
        ManuscriptForm form = new ManuscriptForm();
        form.setManuscriptId(Integer.valueOf(request.getParameter("manuscriptId")));
        form.setRemark(request.getParameter("remark"));
        form.setOpDate(new Date());
        form.setOperId(SessionCache.getSessionValue().getUserId());
        form.setOpType(MmOpTypeEnum.ADVICE.getId());

        manuscriptService.advice(form);
        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptPeriodInit")
    public ModelAndView manuscriptPeriodInit(HttpServletRequest request,
                                             HttpServletResponse response) {
        return new ModelAndView("../../jsp/manuscript/ManuscriptPeriod");
    }

    @RequestMapping(value = "manuscriptPeriod")
    public ModelAndView manuscriptPeriod(HttpServletRequest request,
                                         HttpServletResponse response, ManuscriptDeliverForm form) {
        String periodIdStr = request.getParameter("periodId");
        if (StringUtils.hasText(periodIdStr)) {
            String[] manuscriptIds = (String[]) SessionCache.get(SessionCacheKey.DELIVER_MANUSCRIPT_ID);
            for (String id : manuscriptIds) {
                manuscriptService.period(Integer.valueOf(id), Integer.valueOf(periodIdStr));
            }
        }

        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptTransferInit")
    public ModelAndView manuscriptTransferInit(HttpServletRequest request,
                                               HttpServletResponse response) {
        User user = userService.get(SessionCache.getSessionValue().getUserId());
        FactoryTypeEnum[] factorys = RoleEnum.getFactoryTypeEnum(user.getRoleId());
        return new ModelAndView("../../jsp/manuscript/ManuscriptTransfer", "command", factorys);
    }

    @RequestMapping(value = "manuscriptTransfer")
    public ModelAndView manuscriptTransfer(HttpServletRequest request,
                                           HttpServletResponse response, ManuscriptDeliverForm form) {
        String factoryIdStr = request.getParameter("factoryId");
        if (StringUtils.hasText(factoryIdStr)) {
            String[] manuscriptIds = (String[]) SessionCache.get(SessionCacheKey.DELIVER_MANUSCRIPT_ID);
            for (String id : manuscriptIds) {
                manuscriptService.transfer(Integer.valueOf(id), Integer.valueOf(factoryIdStr));
            }
        }

        return new ModelAndView("redirect:/manuscriptList");
    }

    @RequestMapping(value = "manuscriptRecordList")
    public ModelAndView manuscriptRecordList(HttpServletRequest request,
                                             HttpServletResponse response) {
        String manuscriptId = request.getParameter("manuscriptId");
        List<ManuscriptRecordListForm> result = manuscriptService.getManuscriptRecordList(Integer.valueOf(manuscriptId));
        return new ModelAndView("../../jsp/record/RecordList", "command", result);
    }


    @RequestMapping(value = "manuscriptList")
    public ModelAndView manuscriptList(HttpServletRequest request,
                                               HttpServletResponse response) {
        resetSearchCondtion(request);
        //List<ManuscriptListForm> result = filterRecyle(manuscriptService.getManuscriptList(SessionCache.getSessionValue().getSearchCondition()));

        Map<String, Object> commandMap = new HashMap<String, Object>();
        commandMap.put("opTypes", RoleEnum.getMmOpTypeEnumOfAll(0));
        //commandMap.put("command", result);
        List<Section> sections = sectionService.getAll();
        commandMap.put("sections", sections);
        commandMap.put("publishYear", this.getPublishYears());
        commandMap.put("condition", SessionCache.getSessionValue().getSearchCondition());
        return new ModelAndView("../../jsp/manuscript/ManuscriptMainList", commandMap);
    }
    @RequestMapping(value = "manuscriptListJson")
    public @ResponseBody List<ManuscriptListForm> manuscriptList(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Page page = new Page();
        page.setCurrentPage(currentPage);
        Constants.page.set(page);
        List<ManuscriptListForm> result = filterRecyle(manuscriptService.getManuscriptList(SessionCache.getSessionValue().getSearchCondition()));

        return result;
    }

    private List<ManuscriptListForm> filterRecyle(List<ManuscriptListForm> manuscriptListForms) {
        boolean isFilter = SessionCache.getSessionValue().getSearchCondition().isRecyle();
        List<ManuscriptListForm> result = new ArrayList<ManuscriptListForm>();
        for (ManuscriptListForm form : manuscriptListForms)
        {
            if ((isFilter && form.getOpType() == 2)
                    || !isFilter && form.getOpType() != 2)
            {
                result.add(form);
            }
        }
        return result;
    }

    private void deleteManuscript(String[] ids) {
        for (String id : ids) {
            manuscriptService.delete(Integer.valueOf(id));
        }
    }

    /**
     * 区别delete,本方法是在数据库中删除数据
     * @param ids
     */
    private void realDeleteManuscript(String[] ids)
    {
        for (String id : ids) {
            manuscriptService.realDelete(Integer.valueOf(id));
        }
    }

    private void getManuscript(String[] ids) {
        for (String id : ids) {
            manuscriptService.get(Integer.valueOf(id));
        }
    }

    private int[] getPublishYears()
    {
        Date now = new Date();
        int realYear = 1900 + now.getYear();
        int[] result = new int[realYear + 2 - 2014];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = 2014 + i;
        }
        return result;
    }

    private void resetSearchCondtion(HttpServletRequest request)
    {
        String ownerId = request.getParameter("ownerId");
        if (null != ownerId)
        {
            SessionCache.getSessionValue().getSearchCondition().setOwnerId(ownerId);
        }
        String section = request.getParameter("section");
        if (null != section)
        {
            SessionCache.getSessionValue().getSearchCondition().setSection(section);
        }
        String publishTime = request.getParameter("publishTime");
        if (null != publishTime)
        {
            SessionCache.getSessionValue().getSearchCondition().setPublishTime(publishTime);
        }
        String publishYear = request.getParameter("publishYear");
        if (null != publishYear)
        {
            SessionCache.getSessionValue().getSearchCondition().setPublishYear(publishYear);
        }
        String content = request.getParameter("content");
        if (null != content)
        {
            SessionCache.getSessionValue().getSearchCondition().setContent(content);
        }
        String isRecyle = request.getParameter("isRecyle");
        if (null != isRecyle)
        {
            SessionCache.getSessionValue().getSearchCondition().setRecyle(Boolean.valueOf(isRecyle));
        }
        String[] factoryIds = request.getParameterValues("factoryId");
        if (factoryIds != null)
        {
            SessionCache.getSessionValue().getSearchCondition().setFactoryIds(factoryIds);
        }
    }
}
