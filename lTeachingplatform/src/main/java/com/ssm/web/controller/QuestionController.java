package com.ssm.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.xdevapi.Session;
import com.ssm.web.pojo.*;

import com.ssm.web.service.QuestionService;
import com.ssm.web.service.ReplyService;
import com.ssm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private  UserService userService;

    @Autowired
    private ReplyService replyService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping("/question.do")
    public ModelAndView getAllQuestions() {
        ModelAndView modelAndView = new ModelAndView();


        List<Question> questionList = questionService.getAllQuestions();

        List<Questionplus> questionplusList = questionService.getQuestionplusList(questionList);
        List<Questionplus> questionplusList_sort =questionService.sortQuestionplusList(questionplusList);
        //区间范围：前闭后开区间
        List<Questionplus> hotQuestionplusList;
        if(questionplusList_sort.size()>=6){
            hotQuestionplusList= questionplusList_sort.subList(0, 5);
        }else {
            hotQuestionplusList=questionplusList_sort;
        }


        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("hotQuestionplusList", hotQuestionplusList);

        modelAndView.setViewName("redirect:/question.do/1");

        return modelAndView;
    }

    @RequestMapping("/submit_question.do")
    public ModelAndView commitQuestion(@RequestBody Question question) {


        if (question != null && question.getContains() != null && question.getQuestioner()!=null) {
            questionService.setQuestion(question);
        }
        ModelAndView modelAndView = getAllQuestions();

        modelAndView.setViewName("redirect:/question.do/1");
        return modelAndView;
    }

    @RequestMapping("/question-show.do/{Id}")
    @ResponseBody
    public ModelAndView showQuestionDetails(@PathVariable Integer Id) {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("questionId", Id);
        questionService.findQuestionAndReplyById(Id, modelAndView);
        modelAndView.setViewName("reply");
        return modelAndView;
    }

    @RequestMapping("/reply.do")
    public  ModelAndView commitReply(@RequestBody Reply reply) {
        ModelAndView modelAndView = new ModelAndView();


        if(reply != null && reply.getReplyContains() != null &&reply.getQuestionId() !=null){
            replyService.setReply(reply);

        }
        //为了刷新热门问题的列表，重新调用查询所有问题的方法（也许还有性能更高的解决方式？？？）
        getAllQuestions();

        HttpSession session = httpServletRequest.getSession();
        int id = Integer.parseInt(session.getAttribute("questionId").toString());
        questionService.findQuestionAndReplyById(id,modelAndView);
        modelAndView.setViewName("reply");
        return modelAndView;
    }

}

