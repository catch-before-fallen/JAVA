package com.ssm.web.pojo;

public class Question {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.questioner
     *
     * @mbggenerated
     */
    private Integer questioner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.contains
     *
     * @mbggenerated
     */
    private String contains;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.id
     *
     * @return the value of question.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.id
     *
     * @param id the value for question.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.questioner
     *
     * @return the value of question.questioner
     *
     * @mbggenerated
     */
    public Integer getQuestioner() {
        return questioner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.questioner
     *
     * @param questioner the value for question.questioner
     *
     * @mbggenerated
     */
    public void setQuestioner(Integer questioner) {
        this.questioner = questioner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.contains
     *
     * @return the value of question.contains
     *
     * @mbggenerated
     */
    public String getContains() {
        return contains;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.contains
     *
     * @param contains the value for question.contains
     *
     * @mbggenerated
     */
    public void setContains(String contains) {
        this.contains = contains == null ? null : contains.trim();
    }
}