package com.yang.software.mm.web.controller;

import com.yang.software.mm.data.user.User;
import com.yang.software.mm.service.ManuscriptService;
import com.yang.software.mm.service.UserService;
import com.yang.software.mm.web.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class UserController{
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "manuscriptService")
    private ManuscriptService manuscriptService;

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

    @RequestMapping(value = "userMain")
    public ModelAndView userMain(HttpServletRequest request,
                                 HttpServletResponse response, UserForm command) {

        List<User> userList = userService.getAll();
        ModelAndView mv = new ModelAndView("../../jsp/user/UserMain", "command",
                userList);
        return mv;
    }

    @RequestMapping(value = "userAddInit")
    public ModelAndView userAddInit(HttpServletRequest request,
                                    HttpServletResponse response, UserForm command) {
        return new ModelAndView("../../jsp/user/UserAdd");
    }

    @RequestMapping(value = "userAdd")
    public ModelAndView userAdd(HttpServletRequest request,
                                HttpServletResponse response, UserForm command) {

        userService.add(command.getUser());

        return new ModelAndView("redirect:/userMain");
    }

    @RequestMapping(value = "userEditInit")
    public ModelAndView userEditInit(HttpServletRequest request,
                                     HttpServletResponse response, UserForm command) {

        User user = userService.get(command.getId());

        ModelAndView mv = new ModelAndView("../../jsp/user/UserEdit", "user", user);
        return mv;
    }

    @RequestMapping(value = "userEdit")
    public ModelAndView userEdit(HttpServletRequest request,
                                 HttpServletResponse response, UserForm command) {

        userService.modify(command.getUser());

        return new ModelAndView("redirect:/userMain");
    }

    @RequestMapping(value = "resetManuscript")
    public ModelAndView resetManuscript(HttpServletRequest request,
                                 HttpServletResponse response, UserForm command) {

        manuscriptService.resetManuscript();

        return new ModelAndView("redirect:/userMain");
    }


}
