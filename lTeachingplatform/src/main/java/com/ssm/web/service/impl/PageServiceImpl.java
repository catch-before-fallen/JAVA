package com.ssm.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.web.pojo.*;
import com.ssm.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private HomeworkDetailService homeworkDetailService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassResourceService classResourceService;
    @Autowired
    private UserService userService;


    //    PageInfo{pageNum=2, pageSize=6, size=6, startRow=7,
// endRow=12, total=14, pages=3, list=Page{count=true, pageNum=2,
// pageSize=6, startRow=6, endRow=12, total=14, pages=3, reasonable=false,
// pageSizeZero=false}[Reply{id=11, replyer=6, replyContains='123312', questionId=1},
// ], prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true, navigatePages=2,
// navigateFirstPage=1, navigateLastPage=2, navigatepageNums=[1, 2]}
    @Override
    public PageInfo<Reply> getreplyPageInfo(Integer Id, Integer questionId, ModelAndView modelAndView) {

        PageHelper.startPage(Id, 6);
        List<Reply> replyList = replyService.getReplysByQuestionId(questionId);
        PageInfo<Reply> replyPageInfo = new PageInfo<>(replyList, 3);

        Question question = questionService.getQuestionById(questionId);
        User questionerObj = userService.getUserById(questionId);
        Questionplus questionplus = questionService.getQuestionplusObj(question);

        List<Replyplus> replyplusList = replyService.getReplyplusList(replyList);

        questionplus.setReplys(replyplusList);
        questionplus.setQuestionerobj(questionerObj);
        modelAndView.addObject("replyPageInfo", replyPageInfo);
        modelAndView.addObject("questionAndReply", questionplus);
        return replyPageInfo;
    }

    @Override
    public PageInfo<Question> getquestionPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 3);
        List<Question> questionList = questionService.getAllQuestions();
        PageInfo<Question> questionPageInfo = new PageInfo<>(questionList, 3);

        List<Questionplus> questionplusList = questionService.getQuestionplusList(questionList);


        modelAndView.addObject("questionPageInfo", questionPageInfo);
        modelAndView.addObject("questionList", questionplusList);
        return questionPageInfo;
    }

    @Override
    public PageInfo<Homework> getHomeworkPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 8);
        List<Homework> homeworkList = homeworkService.getAllHomework();
        PageInfo<Homework> homeworkPageInfo = new PageInfo<>(homeworkList, 3);

        List<Homeworkplus> homeworkplusList = homeworkService.getHomeworkplusListByHomeworkList(homeworkList);

        modelAndView.addObject("homeworkPageInfo", homeworkPageInfo);
        modelAndView.addObject("homeworkList", homeworkplusList);
        return homeworkPageInfo;
    }

    @Override
    public PageInfo<HomeworkDetail> getHomeworkDetailPageInfo(Integer homeworkId, Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 6);
        List<HomeworkDetail> homeworkDetailList = homeworkDetailService.getsubmitListByHomeworkId(homeworkId);
        PageInfo<HomeworkDetail> homeworkDetailPageInfo = new PageInfo<>(homeworkDetailList, 3);

        Homeworkplus homeworkplus = homeworkService.getHomeworkplusById(homeworkId);

        modelAndView.addObject("homeworkDetail", homeworkplus);
        modelAndView.addObject("homeworkDetailPageInfo", homeworkDetailPageInfo);
        return null;
    }

    @Override
    public PageInfo<Course> getClassResourcePageInfo(Integer id, ModelAndView modelAndView) {

        PageHelper.startPage(id, 5);
        List<Course> courseList = classResourceService.getAllCourse();
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList, 3);

        List<Courseplus> courseplusList = classResourceService.getCourseplusListByCourseList(courseList);


        modelAndView.addObject("coursePageInfo", coursePageInfo);
        modelAndView.addObject("courseList", courseplusList);
        return coursePageInfo;
    }

    @Override
    public PageInfo<Course> getClassplayPageInfo(Integer id, ModelAndView modelAndView) {

        PageHelper.startPage(id, 5);
        List<Course> courseList = courseService.getAllCourse();
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList, 3);

        modelAndView.addObject("courseList", courseList);
        modelAndView.addObject("coursePageInfo", coursePageInfo);

        return coursePageInfo;
    }

    @Override
    public ModelAndView getAbstract(ModelAndView modelAndView) {


        Integer questionSize = (Integer) questionService.getAllQuestions().size();
        Integer homeworkSize = (Integer) homeworkService.getAllHomework().size();
        Integer submitSize = homeworkService.getTotalSubmitSize();
        Integer classSize = (Integer) courseService.getAllCourse().size();

        List<Question> questionList = questionService.getAllQuestions();
        List<Question> questions;
        if (questionList.size() > 6) {
            questions = questionList.subList(0, 5);
        } else {
            questions = questionList;
        }


        List<Homework> homeworkList = homeworkService.getAllHomework();
        List<Homework> homework;
        if (homeworkList.size() > 6) {
            homework = homeworkList.subList(0, 5);
        } else {
            homework = homeworkList;
        }

        modelAndView.addObject("sub_question",questions);
        modelAndView.addObject("sub_homework",homework);
        modelAndView.addObject("questionSize", questionSize);
        modelAndView.addObject("homeworkSize", homeworkSize);
        modelAndView.addObject("submitSize", submitSize);
        modelAndView.addObject("classSize", classSize);


        return modelAndView;
    }

    @Override
    public PageInfo<Reply> getAnswerPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 6);
        List<Reply> replyList = replyService.getAllReplys();
        PageInfo<Reply> replyPageInfo = new PageInfo<>(replyList, 3);

        List<Replyplus> replyplusList = replyService.getReplyplusList(replyList);

        modelAndView.addObject("replyList",replyplusList);
        modelAndView.addObject("replyPageInfo",replyPageInfo);
        return replyPageInfo;
    }

    @Override
    public PageInfo<User> getStudentPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 6);
        List<User> userList = userService.getUserByRole(0);
        PageInfo<User> studentPageInfo = new PageInfo<>(userList, 3);

        modelAndView.addObject("studentList",userList);
        modelAndView.addObject("studentPageInfo",studentPageInfo);
        return studentPageInfo;
    }

    @Override
    public PageInfo<User> getTeacherPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 6);
        List<User> userList = userService.getUserByRole(1);
        PageInfo<User> teacherPageInfo = new PageInfo<>(userList, 3);

        modelAndView.addObject("teacherList",userList);
        modelAndView.addObject("teacherPageInfo",teacherPageInfo);
        return teacherPageInfo;
    }

    @Override
    public PageInfo<HomeworkDetail> getSubmitPageInfo(Integer id, ModelAndView modelAndView) {
        PageHelper.startPage(id, 6);
        List<HomeworkDetail> homeworkDetailList=homeworkDetailService.getAllsubmitList();
        PageInfo<HomeworkDetail> homeworkDetailPageInfo = new PageInfo<>(homeworkDetailList, 3);

        List<HomeworkDetailplus> homeworkDetailplusList = homeworkDetailService.getHomeworkDetailList(homeworkDetailList);

        modelAndView.addObject("homeworkDetailList",homeworkDetailplusList);
        modelAndView.addObject("homeworkDetailPageInfo",homeworkDetailPageInfo);
        return null;
    }
}
