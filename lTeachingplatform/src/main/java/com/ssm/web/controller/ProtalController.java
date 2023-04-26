package com.ssm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProtalController {


    @RequestMapping("/register-inner.do")
    String returnRegisterInner(){
        return "register-inner";
    }
    @RequestMapping("/login-inner.do")
    String returnLoginInner(){
        return "login-inner";
    }
    @RequestMapping("/course_list.do")
    String returnAdminpage(){
        return "admin/course_list";
    }




}
