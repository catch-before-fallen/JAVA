package com.ssm.web.pojo;

public class Course {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.uploader
     *
     * @mbggenerated
     */
    private Integer uploader;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.course_name
     *
     * @mbggenerated
     */
    private String courseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.kinds
     *
     * @mbggenerated
     */
    private String kinds;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.id
     *
     * @return the value of course.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.id
     *
     * @param id the value for course.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.uploader
     *
     * @return the value of course.uploader
     *
     * @mbggenerated
     */
    public Integer getUploader() {
        return uploader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.uploader
     *
     * @param uploader the value for course.uploader
     *
     * @mbggenerated
     */
    public void setUploader(Integer uploader) {
        this.uploader = uploader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.course_name
     *
     * @return the value of course.course_name
     *
     * @mbggenerated
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.course_name
     *
     * @param courseName the value for course.course_name
     *
     * @mbggenerated
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.title
     *
     * @return the value of course.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.title
     *
     * @param title the value for course.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.kinds
     *
     * @return the value of course.kinds
     *
     * @mbggenerated
     */
    public String getKinds() {
        return kinds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.kinds
     *
     * @param kinds the value for course.kinds
     *
     * @mbggenerated
     */
    public void setKinds(String kinds) {
        this.kinds = kinds == null ? null : kinds.trim();
    }

}