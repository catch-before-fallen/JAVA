package com.ssm.web.pojo;

public class Courseplus {


    private Course parent;
    private User uploader;

    public Courseplus() {
    }

    public Courseplus(Course parent) {
        this.parent = parent;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Course getParent() {
        return parent;
    }

    public void setParent(Course parent) {
        this.parent = parent;
    }
}
