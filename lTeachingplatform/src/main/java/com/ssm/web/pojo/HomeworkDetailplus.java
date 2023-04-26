package com.ssm.web.pojo;

public class HomeworkDetailplus {

    private HomeworkDetail parent;
    private String submitTimeStr;
    private User submiter;

    public HomeworkDetailplus() {
    }

    public HomeworkDetailplus(HomeworkDetail parent) {
        this.parent = parent;
    }

    public HomeworkDetail getParent() {
        return parent;
    }

    public void setParent(HomeworkDetail parent) {
        this.parent = parent;
    }

    public User getSubmiter() {
        return submiter;
    }

    public void setSubmiter(User submiter) {
        this.submiter = submiter;
    }

    public String getSubmitTimeStr() {
        return submitTimeStr;
    }

    public void setSubmitTimeStr(String submitTimeStr) {
        this.submitTimeStr = submitTimeStr;
    }
}
