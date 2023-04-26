package com.ssm.web.service;

import com.ssm.web.pojo.Homework;
import com.ssm.web.pojo.HomeworkDetailplus;
import com.ssm.web.pojo.Homeworkplus;

import java.util.Collection;
import java.util.List;

public interface HomeworkService {

    //获取所有的作业
    List<Homework> getAllHomework();
    //根据Id获取作业
    Homework getHomeworkById(Integer Id);
    //根据List<Homework>返回List<Homeworkplus>
    List<Homeworkplus> getHomeworkplusListByHomeworkList(List<Homework> homeworkList);
    //根据时间排序列表，返回一个有序列表用来显示最新的作业的提交情况
    List<Homeworkplus> sorttHomeworkplusList(List<Homeworkplus>  homeworkplusList);
    //根据Homework返回Homeworkplus
    Homeworkplus getHomeworkplusByHomework(Homework homework);
// 根据Id返回作业实体对象
    Homeworkplus getHomeworkplusById(Integer id);

    void insertHomework(Homework homework);
    //获取提交总数
    Integer getTotalSubmitSize();

    void deleHomeworkById(Integer id);
    //获取最近布置的列表

}
