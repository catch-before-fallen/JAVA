package com.ssm.web.service;

import com.ssm.web.pojo.Question;
import com.ssm.web.pojo.Questionplus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {
    //获取全部问题
    List<Question> getAllQuestions();
    //根据Id获取question
    Question getQuestionById(Integer id);

//根据List<question>获取List<questionPlus>
    List<Questionplus> getQuestionplusList(List<Question> questionList);
    //根据List<question>获取List<questionPlus>
    Questionplus getQuestionplusObj(Question question);
    //根据Id查找questionplus，并且将里面的内容填充完整
    ModelAndView findQuestionAndReplyById(Integer id, ModelAndView modelAndView);
//插入问题
    void setQuestion(Question question);
//将List<Questionplus>按照回复数排序
    List<Questionplus> sortQuestionplusList(List<Questionplus> questionplusList);

    void deleQuestionById(Integer id);
}
