package com.ssm.web.pojo;

public class Reply {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reply.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reply.replyer
     *
     * @mbggenerated
     */
    private Integer replyer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reply.reply_contains
     *
     * @mbggenerated
     */
    private String replyContains;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column reply.question_id
     *
     * @mbggenerated
     */
    private Integer questionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reply.id
     *
     * @return the value of reply.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reply.id
     *
     * @param id the value for reply.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reply.replyer
     *
     * @return the value of reply.replyer
     *
     * @mbggenerated
     */
    public Integer getReplyer() {
        return replyer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reply.replyer
     *
     * @param replyer the value for reply.replyer
     *
     * @mbggenerated
     */
    public void setReplyer(Integer replyer) {
        this.replyer = replyer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reply.reply_contains
     *
     * @return the value of reply.reply_contains
     *
     * @mbggenerated
     */
    public String getReplyContains() {
        return replyContains;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reply.reply_contains
     *
     * @param replyContains the value for reply.reply_contains
     *
     * @mbggenerated
     */
    public void setReplyContains(String replyContains) {
        this.replyContains = replyContains == null ? null : replyContains.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column reply.question_id
     *
     * @return the value of reply.question_id
     *
     * @mbggenerated
     */
    public Integer getQuestionId() {
        return questionId;
    }



    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column reply.question_id
     *
     * @param questionId the value for reply.question_id
     *
     * @mbggenerated
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", replyer=" + replyer +
                ", replyContains='" + replyContains + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}