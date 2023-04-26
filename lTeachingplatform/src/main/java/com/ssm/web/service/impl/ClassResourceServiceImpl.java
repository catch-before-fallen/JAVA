package com.ssm.web.service.impl;

import com.ssm.web.mapper.CourseMapper;
import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.Course;
import com.ssm.web.pojo.CourseExample;
import com.ssm.web.pojo.Courseplus;
import com.ssm.web.pojo.User;
import com.ssm.web.service.ClassResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ClassResourceServiceImpl implements ClassResourceService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;

    private CourseExample courseExample=new CourseExample();


    @Override
    public List<Course> getAllCourse() {
        courseExample.clear();;
        courseExample.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(courseExample);


        courseList.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return courseList;
    }

    @Override
    public List<Courseplus> getCourseplusListByTeacherId(Integer teacher) {
        courseExample.clear();
        courseExample.createCriteria().andUploaderEqualTo(teacher);

        List<Course> courseList = courseMapper.selectByExample(courseExample);
        List<Courseplus> courseplusList = getCourseplusListByCourseList(courseList);
        return courseplusList;
    }

    @Override
    public List<Courseplus> sortCourseList(List<Courseplus> courseplusList) {
        courseplusList.sort(new Comparator<Courseplus>() {
            @Override
            public int compare(Courseplus o1, Courseplus o2) {
                return o1.getParent().getUploader()-o2.getParent().getUploader();
            }
        });
        return courseplusList;
    }


    @Override
    public String getCourseNameById(Integer id) {
        Course course = courseMapper.selectByPrimaryKey(id);

        return   course.getCourseName();
    }



    @Override
    public List<Courseplus> getCourseplusListByCourseList(List<Course> courseList) {

        List<Courseplus> courseplusList= new ArrayList<>();
        courseList.forEach(item->{
            Courseplus courseplus = new Courseplus(item);

            User user = userMapper.selectByPrimaryKey(item.getUploader());

            courseplus.setUploader(user);
            courseplusList.add(courseplus);
        });
        return courseplusList;
    }

    @Override
    public Courseplus getCourseplusByCourseId(Integer id) {

        Course course = courseMapper.selectByPrimaryKey(id);
        Courseplus courseplus = new Courseplus(course);

        User user = userMapper.selectByPrimaryKey(course.getUploader());

        courseplus.setUploader(user);

        return courseplus;
    }

    @Override
    public String getCourseTitleById(Integer id) {
        Course course = courseMapper.selectByPrimaryKey(id);

        return   course.getTitle();
    }

    @Override
    public void addCourse(MultipartFile multipartFile, HttpSession session) throws Exception {

        User curruser = (User)session.getAttribute("curruser");
        Integer uploader;
        if(curruser.getRole()==1){
           uploader = curruser.getId();
        }else {
            throw new Exception("非管理员执行提交课程操作");
        }


        String courseName="计算机网络";

        String originalFilename= multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String title=originalFilename.substring(4,index);

        String kinds= multipartFile.getContentType();

        Course course = new Course();
        course.setUploader(uploader);
        course.setCourseName(courseName);
        course.setTitle(title);
        course.setKinds(kinds);

        courseMapper.insert(course);

    }



}
