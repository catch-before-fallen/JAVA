package com.ssm.web.service;

import com.ssm.web.pojo.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

public interface UserService {

//    教师登录
    User login(String userName, String password);
    User getUserById(Integer Id);

    boolean addUser(User user) ;

   List<User> getUserByRole(Integer i);
}
