package com.ssm.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.web.mapper.HomeworkMapper;
import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.*;
import com.ssm.web.service.HomeworkDetailService;
import com.ssm.web.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HomeworkDetailService homeworkDetailService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    private HomeworkExample homeworkExample=new HomeworkExample();
    

    @Override
    public List<Homework> getAllHomework() {
        
        homeworkExample.clear();
        homeworkExample.createCriteria();
        List<Homework> homeworkList = homeworkMapper.selectByExample(homeworkExample);

        //应为查询使用的Mapper中是逆向工程生成的,通过它查询的数据不会去实体类中读对应注解，对于spring的格式注解：
        //@Jsonformat 和@DateTimeFormat不生效,Date类的数据格式处理不到位，也不方便直接修改
        //所以需要自己格式化日期格式


        return homeworkList;
    }

    @Override
    public Homework getHomeworkById(Integer Id) {

        return homeworkMapper.selectByPrimaryKey(Id);
    }

    @Override
    public List<Homeworkplus> getHomeworkplusListByHomeworkList(List<Homework> homeworkList) {
        //将id转化为实体对象
        List<Homeworkplus> homeworkplusList =new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Homework homework :homeworkList) {
            Integer teacherId = homework.getTeacher();
            Integer homeworkId = homework.getId();
            User teacher = userMapper.selectByPrimaryKey(teacherId);

            Homeworkplus homeworkplus = new Homeworkplus(homework);

            List<HomeworkDetail> homeworkDetailList=homeworkDetailService.getsubmitListByHomeworkId(homeworkId);

            List<HomeworkDetailplus> homeworkDetailplusList=homeworkDetailService.getHomeworkDetailList(homeworkDetailList);

            //格式化时间
            homeworkplus.setAssignTimeStr(simpleDateFormat.format(homework.getAssignTime()));

            homeworkplus.setTeacher(teacher);
            homeworkplus.setSubmitlist(homeworkDetailplusList);
            homeworkplusList.add(homeworkplus);

        }
        return homeworkplusList;
    }

    @Override
    public List<Homeworkplus> sorttHomeworkplusList(List<Homeworkplus> homeworkplusList) {
        homeworkplusList.sort(new Comparator<Homeworkplus>() {
            @Override
            public int compare(Homeworkplus o1, Homeworkplus o2) {
                //Date类型的数据比较可以用compareTo返回正值表示左值比右值大，也就是左边日期比右边更近
                return o2.getParent().getAssignTime().compareTo(o1.getParent().getAssignTime());
            }
        });
        return homeworkplusList;
    }

    @Override
    public Homeworkplus getHomeworkplusByHomework(Homework homework) {
        Integer teacherId = homework.getTeacher();
        Integer homeworkId = homework.getId();
        User teacher = userMapper.selectByPrimaryKey(teacherId);

        Homeworkplus homeworkplus = new Homeworkplus(homework);

        List<HomeworkDetail> homeworkDetailList=homeworkDetailService.getsubmitListByHomeworkId(homeworkId);


        List<HomeworkDetailplus> homeworkDetailplusList=homeworkDetailService.getHomeworkDetailList(homeworkDetailList);

        //格式化时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        homeworkplus.setAssignTimeStr(simpleDateFormat.format(homework.getAssignTime()));



        homeworkplus.setTeacher(teacher);
        homeworkplus.setSubmitlist(homeworkDetailplusList);

        return homeworkplus;
    }

    @Override
    public Homeworkplus getHomeworkplusById(Integer id) {

        Homework homework = getHomeworkById(id);
        Homeworkplus homeworkplus = getHomeworkplusByHomework(homework);
        return homeworkplus;
    }

    @Override
    public void insertHomework(Homework homework) {
        homeworkMapper.insertSelective(homework);
    }

    @Override
    public Integer getTotalSubmitSize() {

        int totalSize;

        List<Homework> homeworkList = getAllHomework();
        totalSize = homeworkList.stream().mapToInt(item -> homeworkDetailService.getsubmitListByHomeworkId(item.getId()).size()).sum();


        return (Integer)totalSize;
    }

    @Override
    public void deleHomeworkById(Integer id) {
        //先删除掉作业详细，也就是提交的作业
        List<HomeworkDetail> homeworkDetailList = homeworkDetailService.getsubmitListByHomeworkId(id);
        //如果不为空才能调用forEach方法，否则会异常
        //因为forEach底层代码:
        // Objects.requireNonNull(action);->要求对象不能为null
        if(homeworkDetailList!=null){
            homeworkDetailList.forEach(item->{
                homeworkDetailService.deleHomeworkDetailById(item.getId());
            });
        }

        //再删除布置的作业
        homeworkMapper.deleteByPrimaryKey(id);
    }


}
