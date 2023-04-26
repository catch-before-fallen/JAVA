package com.ssm.web.controller;

import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.ssm.web.pojo.User;
import com.ssm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/teacher-login/login.do" )

    public String teacherLogin( String userName, String password, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();


            User user=userService.login(userName,password);

//        设置一个假数据方便访问
//        User user = new User();
//        user.setId(10);
//        user.setUserName("教师测试用例");
//        user.setEmail("123@qq.com");
//        user.setPwd("123");
//        user.setRole(1);
        if(user != null && user.getRole()==1){
            //表示登录的是老师
            session.setAttribute("curruser",user);
            return "redirect:/admin/";
        }
       servletRequest.setAttribute("message","输入教师信息错误，重新输入！");
        return "login";
    }

    @RequestMapping("/student-login.do")
    @ResponseBody
    public String studentLogin(String userName, String password, HttpServletRequest servletRequest) throws Exception {
        HttpSession session = servletRequest.getSession();

//       User user=userService.login(userName,password);
       //        设置一个假数据方便访问
        User user = new User();
        user.setId(6);
        user.setUserName("test");
        user.setEmail("123@qq.com");
        user.setPwd("123");
        user.setRole(0);
        if(user!=null && user.getRole()==0){
            //表示登录的学生
            session.setAttribute("curruser",user);
            return "success";

        }
        String errormsg="输入学生信息错误，重新输入！";
        throw new Exception(errormsg);
    }
    @RequestMapping("/teacher-login.do")
    public  String teacherLogin(){
        return "login";
    }
    @RequestMapping("/logout.do")
    public String logout(HttpSession httpSession){

        httpSession.removeAttribute("curruser");
        return "home";
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public ModelAndView studentRegister(@RequestBody  User user)  {
        ModelAndView modelAndView = new ModelAndView();
        boolean addUser = userService.addUser(user);
        if(!addUser){
            modelAndView.addObject("errormsg","用户已经注册");
        }
        modelAndView.setViewName("register-inner");
        return modelAndView;
    }
}
