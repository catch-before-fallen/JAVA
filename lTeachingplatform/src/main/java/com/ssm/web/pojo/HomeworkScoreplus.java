package com.ssm.web.pojo;

public class HomeworkScoreplus {

    private HomeworkScore parent;
    private User scorer;

    public HomeworkScoreplus() {
    }

    public HomeworkScoreplus(HomeworkScore parent) {
        this.parent = parent;
    }

    public HomeworkScore getParent() {
        return parent;
    }

    public void setParent(HomeworkScore parent) {
        this.parent = parent;
    }

    public User getScorer() {
        return scorer;
    }

    public void setScorer(User scorer) {
        this.scorer = scorer;
    }
}
