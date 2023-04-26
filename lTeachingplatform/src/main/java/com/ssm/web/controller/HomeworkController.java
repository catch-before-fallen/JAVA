package com.ssm.web.controller;

import com.ssm.web.pojo.Homework;
import com.ssm.web.pojo.HomeworkDetail;
import com.ssm.web.pojo.HomeworkDetailplus;
import com.ssm.web.pojo.Homeworkplus;
import com.ssm.web.service.HomeworkDetailService;
import com.ssm.web.service.HomeworkService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private HomeworkDetailService homeworkDetailService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/homework.do")
    public ModelAndView showHomeworkPage(){
        ModelAndView modelAndView = new ModelAndView();

        List<Homework> homeworkList=homeworkService.getAllHomework();
        List<Homeworkplus> homeworkplusList = homeworkService.getHomeworkplusListByHomeworkList(homeworkList);
        List<Homeworkplus>  homeworkplusList_sort= homeworkService.sorttHomeworkplusList(homeworkplusList);
        //存在索引越界的风险
        List<Homeworkplus> homeworklist_latest;
        if(homeworkplusList_sort.size()>=6){
            homeworklist_latest= homeworkplusList_sort.subList(0, 5);
        }else {
            homeworklist_latest= homeworkplusList_sort;
        }


        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("latestList", homeworklist_latest);

        modelAndView.setViewName("redirect:/homework.do/1");
        return modelAndView;
    }

    @RequestMapping("/homework-show.do/{Id}")
    public ModelAndView showHomeworkDetailsPage(@PathVariable String Id){
        ModelAndView modelAndView = new ModelAndView();
        Integer id=Integer.parseInt(Id);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("homeworkId",id);

        modelAndView.setViewName("redirect:/homework-detail.do/1");
        return modelAndView;
    }

    @RequestMapping("/homework_submit.do")
//    details: reply,
//    submiter: submiterid,
//    submitTime:submitTime,
//    homeworkId:homeworkid
    public  ModelAndView submitHomework(@RequestBody HomeworkDetail homeworkDetail) {
        ModelAndView modelAndView = new ModelAndView();

        homeworkDetailService.insertHomeworkDetail(homeworkDetail);

       modelAndView.setViewName("redirect:/homework-detail.do/1");
        return  modelAndView;
    }
    @RequestMapping("/assign_homework.do")
    public  ModelAndView assignHomework(@RequestBody Homework homework){
        ModelAndView modelAndView = new ModelAndView();
        if(homework!=null){
            homeworkService.insertHomework(homework);
        }
        modelAndView = showHomeworkPage();

        modelAndView.setViewName("redirect:/homework.do/1");
        return  modelAndView;
    }
}
