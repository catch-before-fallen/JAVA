package com.ssm.web.pojo;

import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table question
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table question
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table question
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public QuestionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table question
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andQuestionerIsNull() {
            addCriterion("questioner is null");
            return (Criteria) this;
        }

        public Criteria andQuestionerIsNotNull() {
            addCriterion("questioner is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionerEqualTo(Integer value) {
            addCriterion("questioner =", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerNotEqualTo(Integer value) {
            addCriterion("questioner <>", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerGreaterThan(Integer value) {
            addCriterion("questioner >", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerGreaterThanOrEqualTo(Integer value) {
            addCriterion("questioner >=", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerLessThan(Integer value) {
            addCriterion("questioner <", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerLessThanOrEqualTo(Integer value) {
            addCriterion("questioner <=", value, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerIn(List<Integer> values) {
            addCriterion("questioner in", values, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerNotIn(List<Integer> values) {
            addCriterion("questioner not in", values, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerBetween(Integer value1, Integer value2) {
            addCriterion("questioner between", value1, value2, "questioner");
            return (Criteria) this;
        }

        public Criteria andQuestionerNotBetween(Integer value1, Integer value2) {
            addCriterion("questioner not between", value1, value2, "questioner");
            return (Criteria) this;
        }

        public Criteria andContainsIsNull() {
            addCriterion("contains is null");
            return (Criteria) this;
        }

        public Criteria andContainsIsNotNull() {
            addCriterion("contains is not null");
            return (Criteria) this;
        }

        public Criteria andContainsEqualTo(String value) {
            addCriterion("contains =", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsNotEqualTo(String value) {
            addCriterion("contains <>", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsGreaterThan(String value) {
            addCriterion("contains >", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsGreaterThanOrEqualTo(String value) {
            addCriterion("contains >=", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsLessThan(String value) {
            addCriterion("contains <", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsLessThanOrEqualTo(String value) {
            addCriterion("contains <=", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsLike(String value) {
            addCriterion("contains like", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsNotLike(String value) {
            addCriterion("contains not like", value, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsIn(List<String> values) {
            addCriterion("contains in", values, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsNotIn(List<String> values) {
            addCriterion("contains not in", values, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsBetween(String value1, String value2) {
            addCriterion("contains between", value1, value2, "contains");
            return (Criteria) this;
        }

        public Criteria andContainsNotBetween(String value1, String value2) {
            addCriterion("contains not between", value1, value2, "contains");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table question
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table question
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}