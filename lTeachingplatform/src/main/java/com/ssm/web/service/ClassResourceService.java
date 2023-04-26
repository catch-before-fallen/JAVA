package com.ssm.web.service;

import com.ssm.web.pojo.Course;
import com.ssm.web.pojo.Courseplus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ClassResourceService {
    String getCourseNameById(Integer id);


    List<Courseplus> getCourseplusListByCourseList(List<Course> courseList);

    Courseplus getCourseplusByCourseId(Integer id);

    String getCourseTitleById(Integer id);

    void addCourse(MultipartFile multipartFile, HttpSession session) throws Exception;

    List<Course> getAllCourse();

    List<Courseplus> getCourseplusListByTeacherId(Integer teacher);

    List<Courseplus> sortCourseList(List<Courseplus> courseplusList);
}
