package com.ssm.web.controller;


import com.github.pagehelper.PageInfo;
import com.ssm.web.pojo.Reply;
import com.ssm.web.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @RequestMapping({"/home.do","/"})
    public ModelAndView showHomePage(){

        ModelAndView modelAndView = new ModelAndView();

        pageService.getAbstract(modelAndView);


        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping("/classplay.do/{id}")
    public ModelAndView showClassplay(@PathVariable("id") String Id,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        pageService.getClassplayPageInfo(id,modelAndView);

        modelAndView.setViewName("classplay");
        return modelAndView;
    }
    @RequestMapping("/page.do/{id}")
    public ModelAndView showPage(@PathVariable("id") String Id ,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);


        HttpSession session = httpServletRequest.getSession();
        Integer questionId = Integer.parseInt(session.getAttribute("questionId").toString());

        pageService.getreplyPageInfo(id,questionId,modelAndView);

        modelAndView.setViewName("reply");
        return modelAndView;
    }
    @RequestMapping("/question.do/{id}")
    public ModelAndView showQuestion(@PathVariable("id") String Id ,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        pageService.getquestionPageInfo(id,modelAndView);

        modelAndView.setViewName("question");
        return modelAndView;
    }
    @RequestMapping("/homework.do/{id}")
    public ModelAndView showHomework(@PathVariable("id") String Id ,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        pageService.getHomeworkPageInfo(id,modelAndView);

        modelAndView.setViewName("homework");
        return modelAndView;
    }

    @RequestMapping("/homework-detail.do/{id}")
    public ModelAndView showHomeworkDetail(@PathVariable("id") String Id ,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);
        //空指针
        HttpSession session = httpServletRequest.getSession();
        Integer homeworkId = Integer.parseInt(session.getAttribute("homeworkId").toString());
        pageService.getHomeworkDetailPageInfo(homeworkId,id,modelAndView);

        modelAndView.setViewName("homework-detail");
        return modelAndView;
    }
    @RequestMapping("/classresource.do/{id}")
    public ModelAndView showClassResource(@PathVariable("id") String Id,HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        pageService.getClassResourcePageInfo(id,modelAndView);

        modelAndView.setViewName("classresource");
        return modelAndView;
    }




}
