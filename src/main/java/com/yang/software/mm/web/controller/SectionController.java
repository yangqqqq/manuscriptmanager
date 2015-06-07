package com.yang.software.mm.web.controller;

import com.yang.software.mm.data.section.Section;
import com.yang.software.mm.service.SectionService;
import com.yang.software.mm.web.form.SectionForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import utils.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class SectionController{
    @Resource(name = "sectionService")
    private SectionService sectionService;

    public SectionService getSectionService() {
        return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "sectionMain")
    public ModelAndView sectionMain(HttpServletRequest request,
                                    HttpServletResponse response, SectionForm command) {

        List<Section> sectionList = sectionService.getAll();
        ModelAndView mv = new ModelAndView("../../jsp/section/SectionMain", "command",
                sectionList);
        return mv;
    }

    @RequestMapping(value = "sectionAddInit")
    public ModelAndView sectionAddInit(HttpServletRequest request,
                                       HttpServletResponse response, SectionForm command) {

        return new ModelAndView("../../jsp/section/SectionAdd");
    }

    @RequestMapping(value = "sectionAdd")
    public ModelAndView sectionAdd(HttpServletRequest request,
                                   HttpServletResponse response, SectionForm command) {
        sectionService.add(command.getSection());
        return new ModelAndView("redirect:/sectionMain");
    }

    @RequestMapping(value = "sectionEditInit")
    public ModelAndView sectionEditInit(HttpServletRequest request,
                                        HttpServletResponse response) {
        String sectionId = request.getParameter("id");
        if (!StringUtils.hasText(sectionId)) {
            return new ModelAndView("redirect:/sectionMain");
        }
        Section section = sectionService.get(Integer.valueOf(sectionId));
        return new ModelAndView("../../jsp/section/SectionEdit", "command", section);
    }

    @RequestMapping(value = "sectionEdit")
    public ModelAndView sectionEdit(HttpServletRequest request,
                                    HttpServletResponse response, SectionForm command) {

        sectionService.modify(command.getSection());
        return new ModelAndView("redirect:/sectionMain");
    }

}
