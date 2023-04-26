package com.ssm.web.service.impl;

import com.ssm.web.mapper.HomeworkDetailMapper;
import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.*;
import com.ssm.web.service.HomeworkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HomeworkDetailServiceImpl implements HomeworkDetailService {

    @Autowired
    private HomeworkDetailMapper homeworkDetailMapper;

    @Autowired
    private UserMapper userMapper;

    private HomeworkDetailExample homeworkDetailExample=new HomeworkDetailExample();


    @Override
    public List<HomeworkDetail> getsubmitListByHomeworkId(Integer homeworkId) {
        homeworkDetailExample.clear();
        homeworkDetailExample.createCriteria().andHomeworkEqualTo(homeworkId);

        List<HomeworkDetail> homeworkDetailList = homeworkDetailMapper.selectByExample(homeworkDetailExample);
        return homeworkDetailList;
    }

    @Override
    public List<HomeworkDetailplus> getHomeworkDetailList(List<HomeworkDetail> homeworkDetailList) {
        List<HomeworkDetailplus> homeworkDetailplusList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        homeworkDetailList.forEach(item->{
            Integer submitter = item.getSubmitter();
            User user = userMapper.selectByPrimaryKey(submitter);

            HomeworkDetailplus homeworkDetailplus = new HomeworkDetailplus(item);

            //格式化日期
            homeworkDetailplus.setSubmitTimeStr(simpleDateFormat.format(item.getSubmitTime()));

            homeworkDetailplus.setSubmiter(user);
            homeworkDetailplusList.add(homeworkDetailplus);
        });
        return homeworkDetailplusList;
    }

    @Override
    public void insertHomeworkDetail(HomeworkDetail homeworkDetail) {


        homeworkDetailMapper.insertSelective(homeworkDetail);
    }

    @Override
    public List<HomeworkDetail> getAllsubmitList() {
        homeworkDetailExample.clear();
        homeworkDetailExample.createCriteria();

        List<HomeworkDetail> homeworkDetailList = homeworkDetailMapper.selectByExample(homeworkDetailExample);
        return homeworkDetailList;
    }

    @Override
    public HomeworkDetail getHomeworkDetailById(Integer id) {
        return homeworkDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleHomeworkDetailById(Integer id) {
        homeworkDetailMapper.deleteByPrimaryKey(id);
    }


}
