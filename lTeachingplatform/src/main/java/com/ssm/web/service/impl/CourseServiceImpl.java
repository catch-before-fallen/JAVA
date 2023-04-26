package com.ssm.web.service.impl;

import com.ssm.web.mapper.CourseMapper;
import com.ssm.web.pojo.Course;
import com.ssm.web.pojo.CourseExample;
import com.ssm.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    CourseExample courseExample = new CourseExample();


    @Override
    public Course jumpToCourse(String coursetitle) {
        courseExample.clear();
        courseExample.createCriteria().andTitleEqualTo(coursetitle);

        List<Course> courses = courseMapper.selectByExample(courseExample);
        if(courses.size()!=1){
            return null;
        }
        Course course = courses.get(0);
      return course;
    }

    @Override
    public void deleCourseById(Integer id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Course> getAllCourse() {
       courseExample.clear();


        courseExample.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        //将查询出来的元素进行排序--升序排序
        courseList.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return  courseList;
    }


}
