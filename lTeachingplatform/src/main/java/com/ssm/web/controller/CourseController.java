package com.ssm.web.controller;


import com.ssm.web.pojo.Course;
import com.ssm.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/classplay.do")
    public ModelAndView getAllCourse(ModelAndView modelAndView){

        List<Course> courselist = courseService.getAllCourse();
        modelAndView.addObject("courseList",courselist);
        modelAndView.setViewName("redirect:/classplay.do/1");
        return modelAndView;
    }

    @RequestMapping(
            value = "/changecourse.do")
//    RequestParam作用是获取请求链接后面的参数中key为RequestPara中的1value的键值对的值
// 无法处理json对象也无法获取post方式携带的内容
    public ModelAndView jumpToCourse(@RequestParam(value = "jumpTitle")String coursetitle,ModelAndView modelAndView){


        Course course = courseService.jumpToCourse(coursetitle);

        modelAndView.addObject(course);
        modelAndView.setViewName("redirect:/classplay.do");
        return modelAndView;
    }
}
