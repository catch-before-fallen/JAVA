package com.ssm.web.pojo;

import java.util.List;

//为了实现实体的对应关系，设置了一个Question的扩展类，
// 构造器可以传进来对应的question对象（不需要extendQuestion，太麻烦）
public class Questionplus {

    private Question parent;
    private User questionerobj;
    //这里和数据库表中的对应关系不符合，但是为了完善显示，只能求其次了
    //本来是Reply表根据id找对应的question，这里我把reply放到了对应的Question表中
    private List<Replyplus> replys;
    private Integer replycount;



    public Questionplus() {
    }

    public Questionplus(Question parent) {
        this.parent = parent;
    }

    public Integer getReplycount() {
        return this.replys.size();
    }

    public Question getParent() {
        return parent;
    }


    public void setParent(Question parent) {
        this.parent = parent;
    }





    public User getQuestionerobj() {
        return questionerobj;
    }

    public void setQuestionerobj(User questionerobj) {
        this.questionerobj = questionerobj;
    }

    public List<Replyplus> getReplys() {
        return replys;
    }

    public void setReplys(List<Replyplus> replys) {
        this.replys = replys;
    }
}
