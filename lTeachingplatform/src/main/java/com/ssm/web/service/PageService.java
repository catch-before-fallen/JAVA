package com.ssm.web.service;

import com.github.pagehelper.PageInfo;
import com.ssm.web.pojo.*;
import org.springframework.web.servlet.ModelAndView;



public interface PageService {

//  获取reply分页
    PageInfo<Reply> getreplyPageInfo(Integer id, Integer questionId, ModelAndView modelAndView);

    PageInfo<Question> getquestionPageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<Homework> getHomeworkPageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<HomeworkDetail> getHomeworkDetailPageInfo(Integer homeworkId,Integer id, ModelAndView modelAndView);


    PageInfo<Course> getClassResourcePageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<Course> getClassplayPageInfo(Integer id, ModelAndView modelAndView);

    //获取home页面底部的数据摘要和工作栏的信息
    ModelAndView getAbstract(ModelAndView modelAndView);

    PageInfo<Reply> getAnswerPageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<User> getStudentPageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<User> getTeacherPageInfo(Integer id, ModelAndView modelAndView);

    PageInfo<HomeworkDetail> getSubmitPageInfo(Integer id, ModelAndView modelAndView);
}
