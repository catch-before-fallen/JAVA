package com.ssm.web.service.impl;

import com.ssm.web.mapper.ReplyMapper;
import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.*;
import com.ssm.web.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;

    private ReplyExample replyExample=new ReplyExample();

    @Override
    public void setReply(Reply reply) {

        replyMapper.insert(reply);

    }

    @Override
    public List<Reply> getAllReplys() {
        replyExample.clear();
        replyExample.createCriteria();

        List<Reply> replyList = replyMapper.selectByExample(replyExample);

        return replyList;
    }

    @Override
    public List<Reply> getReplysByQuestionId(Integer questionId) {
        replyExample.clear();
        replyExample.createCriteria().andQuestionIdEqualTo(questionId);

        List<Reply> replyList = replyMapper.selectByExample(replyExample);

        return replyList;
    }

    @Override
    public List<Replyplus> getReplyplusList(List<Reply> replyList) {
        List<Replyplus> replyplusList = new ArrayList<>();
        for (Reply reply: replyList) {
            Replyplus replyplus = new Replyplus(reply);
            Integer replyer = reply.getReplyer();
            User user = userMapper.selectByPrimaryKey(replyer);
            replyplus.setReplyer(user);

            replyplusList.add(replyplus);
        }
        return replyplusList;
    }

    @Override
    public Replyplus getReplyplusObj(Reply reply) {

            Replyplus replyplus = new Replyplus(reply);
            Integer replyer = reply.getReplyer();
            User user = userMapper.selectByPrimaryKey(replyer);
            replyplus.setReplyer(user);


        return  replyplus;
    }

    @Override
    public Integer getReplyCountByQuestionId(Integer questionId) {
       replyExample.clear();
       replyExample.createCriteria().andQuestionIdEqualTo(questionId);

        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        Integer count = replyList.size();

        return count;

    }

    @Override

    public Map<Question,Integer> getReplyCountMapByQuestionList(List<Question> questionList) {

        Map<Question,Integer> replyCountMap = new HashMap<>();
        questionList.forEach(item->{
            replyExample.clear();
            replyExample.createCriteria().andQuestionIdEqualTo(item.getId());

            List<Reply> replyList = replyMapper.selectByExample(replyExample);
            Integer count = replyList.size();
            replyCountMap.put(item,count);
        });

        return replyCountMap;
    }

    @Override
    public void deleReplyById(Integer id) {

         replyMapper.deleteByPrimaryKey(id);

    }

    @Override
    public Integer getQuestionIdByReplyId(Integer id) {
        Reply reply = replyMapper.selectByPrimaryKey(id);

        return  reply.getQuestionId();
    }

}
