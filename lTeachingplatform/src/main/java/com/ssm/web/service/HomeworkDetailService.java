package com.ssm.web.service;

import com.ssm.web.pojo.Homework;
import com.ssm.web.pojo.HomeworkDetail;
import com.ssm.web.pojo.HomeworkDetailplus;

import java.util.Collection;
import java.util.List;

public interface HomeworkDetailService {


    //    根据作业的id获取作业提交列表
    List<HomeworkDetail> getsubmitListByHomeworkId(Integer homeworkId);

    //根据作业详细列表获取List<HomeworkDetailplus>
    List<HomeworkDetailplus> getHomeworkDetailList(List<HomeworkDetail> homeworkDetailList);

    void insertHomeworkDetail(HomeworkDetail homeworkDetail);


    List<HomeworkDetail> getAllsubmitList();

    HomeworkDetail getHomeworkDetailById(Integer id);

    void deleHomeworkDetailById(Integer id);
}
