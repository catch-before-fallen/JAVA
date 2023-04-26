package com.ssm.web.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.web.mapper.QuestionMapper;
import com.ssm.web.mapper.ReplyMapper;
import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.*;
import com.ssm.web.service.QuestionService;
import com.ssm.web.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private  ReplyService replyService;
    @Autowired
    private UserMapper userMapper;


    private QuestionExample questionExample=new QuestionExample();

    @Override
    public List<Question> getAllQuestions() {
        questionExample.clear();

        questionExample.createCriteria();

        List<Question> questionList = questionMapper.selectByExample(questionExample);


        return questionList;
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void setQuestion(Question question) {

        questionMapper.insert(question);
    }

    @Override
    //通过重写比较器的方式将question对象排序
    //其中return 0->默认排序
    //return o1-o2->升序排序
    //return o2-o1->降序排序
    public List<Questionplus> sortQuestionplusList(List<Questionplus> questionplusList) {


        questionplusList.sort(new Comparator<Questionplus>() {
            @Override
            public int compare(Questionplus o1, Questionplus o2) {
                return o2.getReplycount()-o1.getReplycount();
            }
        });
        return questionplusList;
    }

    @Override
    public void deleQuestionById(Integer id) {
        Question question = getQuestionById(id);
        Questionplus questionplus = getQuestionplusObj(question);

        List<Replyplus> replyplusList = questionplus.getReplys();
        if( replyplusList!=null ){
            replyplusList.forEach(item->{
                Integer replyId = item.getParent().getId();
                replyService.deleReplyById(replyId);
            });
        }

        Integer questionId = questionplus.getParent().getId();
        questionMapper.deleteByPrimaryKey(questionId);

    }


    @Override
    public List<Questionplus> getQuestionplusList(List<Question> questionList) {

        //将id转化为实体对象
        List<Questionplus> questionplusList =new ArrayList<>();
        for (Question question :questionList) {
            Integer questionerId = question.getQuestioner();
            User questioner = userMapper.selectByPrimaryKey(questionerId);
            List<Reply> replyList = replyService.getReplysByQuestionId(question.getId());
            List<Replyplus> replyplusList = replyService.getReplyplusList(replyList);

            Questionplus questionplus = new Questionplus(question);
            questionplus.setQuestionerobj(questioner);

            questionplus.setReplys(replyplusList);
            questionplusList.add(questionplus);

        }
        return questionplusList;
    }

    @Override
    public Questionplus getQuestionplusObj(Question question) {

        Integer questionerId = question.getQuestioner();
        User questioner = userMapper.selectByPrimaryKey(questionerId);
        Questionplus questionplus = new Questionplus(question);
        questionplus.setQuestionerobj(questioner);

        return questionplus;
    }




    @Override
    public ModelAndView findQuestionAndReplyById(Integer id, ModelAndView modelAndView) {

        Question question = questionMapper.selectByPrimaryKey(id);
        Integer questioner = question.getQuestioner();
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andQuestionIdEqualTo(id);

        PageHelper.startPage(1,6);
        List<Reply> replyList = replyService.getReplysByQuestionId(id);
        PageInfo<Reply> replyPageInfo = new PageInfo<>(replyList,3);


        List<Replyplus> replyplusList = replyService.getReplyplusList(replyList);



        modelAndView.addObject("replyPageInfo",replyPageInfo);

        Questionplus questionplus = getQuestionplusObj(question);
        //将questioner转化为对应的User实体
        User questionerobj = userMapper.selectByPrimaryKey(questioner);

        questionplus.setReplys(replyplusList);
        questionplus.setQuestionerobj(questionerobj);


        modelAndView.addObject("questionAndReply",questionplus);

        return modelAndView;
    }



}
