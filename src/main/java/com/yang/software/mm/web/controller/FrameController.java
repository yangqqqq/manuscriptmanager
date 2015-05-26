package com.yang.software.mm.web.controller;

import com.yang.software.mm.data.searchcondition.SearchCondition;
import com.yang.software.mm.data.session.SessionCache;
import com.yang.software.mm.data.session.SessionCacheKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FrameController extends SimpleFormController {
    @RequestMapping(value = "/mainFrame")
    public ModelAndView mainFrame(HttpServletRequest request,
                                  HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/MainFrame");
    }

    @RequestMapping(value = "/topFrame")
    public ModelAndView topFrame(HttpServletRequest request,
                                 HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/TopFrame", "command", SessionCache.getSessionValue().getUserName());
    }

    @RequestMapping(value = "/leftFrame")
    public ModelAndView leftFrame(HttpServletRequest request,
                                  HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/LeftFrame");
    }

    @RequestMapping(value = "/rightFrame")
    public ModelAndView rightFrame(HttpServletRequest request,
                                   HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/RightFrame");
    }

    @RequestMapping(value = "/adminMainFrame")
    public ModelAndView adminMainFrame(HttpServletRequest request,
                                       HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/AdminMainFrame");
    }

    @RequestMapping(value = "/adminLeftFrame")
    public ModelAndView adminLeftFrame(HttpServletRequest request,
                                       HttpServletResponse response, Object command) {
        return new ModelAndView("../../jsp/frame/AdminLeftFrame");
    }

    @RequestMapping(value = "/keepLive")
    public ModelAndView keepLive(HttpServletRequest request,
                                 HttpServletResponse response, Object command) {
        return null;
    }

    @RequestMapping(value = "/getSearchCondition")
    public void getSearchCondition(HttpServletRequest request,
                                   HttpServletResponse response) {
        SearchCondition searchCondition = (SearchCondition) SessionCache.get(SessionCacheKey.SEARCH_CONDITION);
        if (searchCondition == null) {
            searchCondition = new SearchCondition();
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(searchCondition.getDisplayString());
        } catch (IOException e) {
        }
    }

    @RequestMapping(value = "/setSearchCondition")
    public void setSearchCondition(HttpServletRequest request,
                                   HttpServletResponse response, Object command) {
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setSearchContext(request.getParameter("searchContext"));
        searchCondition.setSearchPublishTime(request.getParameter("searchPublishTime"));
        searchCondition.setSearchSection(request.getParameter("searchSection"));
        SessionCache.put(SessionCacheKey.SEARCH_CONDITION, searchCondition);
    }

}
