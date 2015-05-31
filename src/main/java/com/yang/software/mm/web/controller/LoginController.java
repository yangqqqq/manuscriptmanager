package com.yang.software.mm.web.controller;

import com.yang.software.mm.data.session.SessionCache;
import com.yang.software.mm.data.session.SessionCacheKey;
import com.yang.software.mm.data.session.SessionValue;
import com.yang.software.mm.data.user.User;
import com.yang.software.mm.enums.RoleEnum;
import com.yang.software.mm.service.ManuscriptService;
import com.yang.software.mm.service.UserService;
import com.yang.software.mm.web.form.LoginForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends SimpleFormController {
    private UserService userService;
    private ManuscriptService manuscriptService;

    @RequestMapping(value = "login")
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response, LoginForm command) {
        int userId = userService.checkUser(command.getUsername(), command.getPassword());
        if (userId != -1) {
            SessionValue sessionValue = new SessionValue();
            sessionValue.setUserName(command.getUsername());
            sessionValue.setUserId(userId);
            SessionCache.setSessionValue(sessionValue);

            SessionCache.put(SessionCacheKey.MANUSCRIPT_LIST_TYPE, 1);
            User user = userService.get(userId);
            SessionCache.put(SessionCacheKey.MANUSCRIPT_OPTYPE_LIST, RoleEnum.getMmOpTypeEnumOfAll(user.getRoleId()));

            if (!command.getUsername().equalsIgnoreCase("admin")) {
                return new ModelAndView("redirect:/mainFrame");
            } else {
                return new ModelAndView("redirect:/adminMainFrame");
            }
        }
        return new ModelAndView("../../jsp/login/Login", "errorInfo", "账号或密码错误");
    }

    @RequestMapping(value = "logoff")
    public ModelAndView logoff(HttpServletRequest request,
                               HttpServletResponse response, LoginForm command) {
        String sessionId = request.getSession().getId();
        SessionCache.removeSession(sessionId);
        return new ModelAndView("redirect:/loginPage");
    }

    @RequestMapping(value = "/loginPage")
    public ModelAndView loginPage(HttpServletRequest request,                                  HttpServletResponse response) {
        return new ModelAndView("../../jsp/login/Login");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ManuscriptService getManuscriptService() {
        return manuscriptService;
    }

    public void setManuscriptService(ManuscriptService manuscriptService) {
        this.manuscriptService = manuscriptService;
    }
}