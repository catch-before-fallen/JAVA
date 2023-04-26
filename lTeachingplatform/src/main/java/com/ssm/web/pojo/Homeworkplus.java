package com.ssm.web.pojo;

import java.util.List;

public class Homeworkplus {

    private Homework parent;
    private User teacher;
    private List<HomeworkDetailplus> submitlist;
    private Integer submitcount;
    private String assignTimeStr;

    public Homeworkplus() {
    }

    public Homeworkplus(Homework parent) {
        this.parent = parent;
    }

    public Homework getParent() {
        return parent;
    }

    public User getTeacher() {
        return teacher;
    }

    public List<HomeworkDetailplus> getSubmitlist() {
        return submitlist;
    }

    public Integer getSubmitcount() {
        return this.submitlist.size();
    }

    public void setParent(Homework parent) {
        this.parent = parent;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public void setSubmitlist(List<HomeworkDetailplus> submitlist) {
        this.submitlist = submitlist;
    }

    public String getAssignTimeStr() {
        return assignTimeStr;
    }

    public void setAssignTimeStr(String assignTimeStr) {
        this.assignTimeStr = assignTimeStr;
    }
}
