package com.ssm.web.pojo;

import java.util.List;

public class Replyplus {

    private Reply parent;
    private User replyer;


    public Replyplus() {
    }

    public Replyplus(Reply parent) {
        this.parent = parent;
    }

    public Reply getParent() {
        return parent;
    }

    public void setParent(Reply parent) {
        this.parent = parent;
    }

    public User getReplyer() {
        return replyer;
    }

    public void setReplyer(User replyer) {
        this.replyer = replyer;
    }
}
