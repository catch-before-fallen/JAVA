package com.ssm.web.service;

import com.ssm.web.pojo.Course;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourse();


    Course jumpToCourse(String coursetitle);


    void deleCourseById(Integer id);


}
