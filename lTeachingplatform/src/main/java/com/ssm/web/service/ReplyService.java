package com.ssm.web.service;

import com.ssm.web.pojo.Question;
import com.ssm.web.pojo.Reply;
import com.ssm.web.pojo.Replyplus;

import java.util.List;
import java.util.Map;

public interface ReplyService {
// 添加回复
    void setReply(Reply reply);
//    查询所有回复
    List<Reply> getAllReplys();
    //    根据问题id查询回复
    List<Reply> getReplysByQuestionId(Integer questionId);

    //根据List<question>获取List<questionPlus>
    List<Replyplus> getReplyplusList(List<Reply> replyList);
    //根据List<question>获取List<questionPlus>
   Replyplus getReplyplusObj(Reply reply);

   Integer getReplyCountByQuestionId(Integer questionId);

    Map<Question,Integer> getReplyCountMapByQuestionList(List<Question> questionList);

    //根据Id删除reply
    void deleReplyById(Integer id);

    Integer getQuestionIdByReplyId(Integer id);
}
