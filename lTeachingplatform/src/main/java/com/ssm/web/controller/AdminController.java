package com.ssm.web.controller;

import com.ssm.web.pojo.HomeworkDetail;
import com.ssm.web.service.*;
import com.ssm.web.utils.UploadUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private HomeworkDetailService homeworkDetailService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ClassResourceService classResourceService;

    //欢迎页访问数据列表的显示
    @RequestMapping({"/","/course_list.do"})
    public String index(){
        return "forward:course_list.do/1";
    }

    @RequestMapping("/course_list.do/{id}")
    public ModelAndView showCourseList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getClassResourcePageInfo(id,modelAndView);

        modelAndView.setViewName("admin/course_list");
        return modelAndView;
    }

    @RequestMapping("/homework_list.do")
    public String index2(){
        return "forward:homework_list.do/1";
    }
    @RequestMapping("/homework_list.do/{id}")
    public ModelAndView showHomeworkList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getHomeworkPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/homework_list");
        return modelAndView;
    }

    @RequestMapping("/submit_list.do")
    public String index7(){
        return "forward:submit_list.do/1";
    }
    @RequestMapping("/submit_list.do/{id}")
    public ModelAndView showHomeworkSubmitList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getSubmitPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/submit_list");
        return modelAndView;
    }

    @RequestMapping("/question_list.do")
    public String index3(){
        return "forward:question_list.do/1";
    }
    @RequestMapping("/question_list.do/{id}")
    public ModelAndView showQuestionList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getquestionPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/question_list");
        return modelAndView;
    }

    @RequestMapping("/answer_list.do")
    public String index4(){
        return "forward:answer_list.do/1";
    }
    @RequestMapping("/answer_list.do/{id}")
    public ModelAndView showAnswerList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getAnswerPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/answer_list");
        return modelAndView;
    }


    @RequestMapping("/student_list.do")
    public String index5(){
        return "forward:student_list.do/1";
    }
    @RequestMapping("/student_list.do/{id}")
    public ModelAndView showStudentList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);

        ModelAndView modelAndView = new ModelAndView();
        pageService.getStudentPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/student_list");
        return modelAndView;
    }


    @RequestMapping("/teacher_list.do")
    public String index6(){
        return "forward:teacher_list.do/1";
    }
    @RequestMapping("/teacher_list.do/{id}")
    public ModelAndView showTeacherList(@PathVariable("id") String Id){
        Integer id = Integer.parseInt(Id);
        ModelAndView modelAndView = new ModelAndView();

        pageService.getTeacherPageInfo(id,modelAndView);

        modelAndView.setViewName("admin/teacher_list");
        return modelAndView;
    }


    //删除图标和查看图标的跳转和业务逻辑处理
    @RequestMapping("/answer_list.do/dele_reply/{id}")
    public ModelAndView deleAnswer(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        replyService.deleReplyById(id);

        pageService.getAnswerPageInfo(1,modelAndView);
        modelAndView.setViewName("admin/answer_list");
        return  modelAndView;
    }
    @RequestMapping("/answer_list.do/show_reply/{id}")
    public ModelAndView showAnswer(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        Integer questionId=replyService.getQuestionIdByReplyId(id);

        pageService.getAnswerPageInfo(1,modelAndView);
        modelAndView.setViewName("redirect:/question-show.do/"+questionId);
        return  modelAndView;
    }

    @RequestMapping("/question_list.do/dele_question/{id}")
    public ModelAndView deleQuestion(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        questionService.deleQuestionById(id);

        pageService.getquestionPageInfo(1,modelAndView);
        modelAndView.setViewName("admin/question_list");
        return  modelAndView;
    }
    @RequestMapping("/question_list.do/show_question/{id}")
    public ModelAndView showQuestion(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        modelAndView.setViewName("redirect:/question-show.do/"+id);
        return  modelAndView;
    }

    @RequestMapping("/submit_list.do/dele_homework_detail/{id}")
    public ModelAndView deleSubmit(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

       homeworkDetailService.deleHomeworkDetailById(id);

        pageService.getSubmitPageInfo(1,modelAndView);
        modelAndView.setViewName("admin/submit_list");
        return  modelAndView;
    }
    @RequestMapping("/submit_list.do/show_homework_detail/{id}")
    public ModelAndView showSubmitDetails(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);
        HomeworkDetail homeworkDetail=homeworkDetailService.getHomeworkDetailById(id);
        Integer homeworkId = homeworkDetail.getHomework();
        modelAndView.setViewName("redirect:/homework-detail.do/"+homeworkId);
        return  modelAndView;
    }

    @RequestMapping("/homework_list.do/dele_homework/{id}")
    public ModelAndView deleHomework(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        homeworkService.deleHomeworkById(id);

        pageService.getHomeworkPageInfo(1,modelAndView);
        modelAndView.setViewName("admin/homework_list");
        return  modelAndView;
    }
    @RequestMapping("/homework_list.do/show_homework/{id}")
    public ModelAndView showHomework(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);


        modelAndView.setViewName("redirect:/homework.do/1");
        return  modelAndView;
    }

    @RequestMapping({"/dele_course/{id}","/course_list.do/dele_course/{id}"})
    public ModelAndView deleCourse(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        courseService.deleCourseById(id);

        pageService.getAnswerPageInfo(1,modelAndView);
        modelAndView.setViewName("admin/course_list");
        return  modelAndView;
    }
    @RequestMapping({"/show_course/{id}","/course_list.do/show_course/{id}"})
    public ModelAndView showCourse(@PathVariable("id") String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = Integer.parseInt(Id);

        modelAndView.setViewName("redirect:/course-show.do/"+id);
        return  modelAndView;
    }



    //添加页面的跳转和业务逻辑处理
    @RequestMapping("/homework_add.do")
    public ModelAndView addHomework(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("admin/homework_add");
        return modelAndView;
    }
    @RequestMapping("/homework_add/assign_homework.do")
    public ModelAndView jumptoHomeworkController(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("forward:/assign_homework.do");
        return  modelAndView;
    }
    @RequestMapping("/question_add.do")
    public ModelAndView addQuestion(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("admin/question_add");
        return modelAndView;
    }
    @RequestMapping("/question_add/submit_question.do")
    public ModelAndView jumptoQuestionController(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("forward:/submit_question.do");
        return  modelAndView;
    }
    @RequestMapping("/course_add.do")
    public ModelAndView addCourse(){
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("admin/course_add");
        return modelAndView;
    }

    //课程上传

    @RequestMapping("/upload.do")
    public ModelAndView jumptoCourseController(MultipartFile multipartFile) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        if (multipartFile.getSize()!=0){
            HttpSession session = httpServletRequest.getSession();
            UploadUtils.transfromMutliflie(multipartFile,session);

            classResourceService.addCourse(multipartFile,session);
        }
        modelAndView.setViewName("admin/course_add");
        return modelAndView;
    }

}
