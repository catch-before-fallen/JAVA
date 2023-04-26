package com.ssm.web.service.impl;

import com.ssm.web.mapper.UserMapper;
import com.ssm.web.pojo.User;
import com.ssm.web.pojo.UserExample;
import com.ssm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private UserExample userExample = new UserExample();


    @Override
    public User login(String userName, String password) {

        userExample.clear();
        if(userName !=null && password !=null) {
            userExample.createCriteria().andUserNameEqualTo(userName).andPwdEqualTo(password);

        }
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null &&userList.size()==1) {
            User user = userList.get(0);
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(Integer Id) {
        return userMapper.selectByPrimaryKey(Id);
    }

    @Override
    public boolean addUser(User user)  {
        userExample.clear();
        userExample.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(userExample);
        if(!userList.isEmpty()) {
           return false;
        }
        userMapper.insertSelective(user);
        return true;
    }

    @Override
    public List<User> getUserByRole(Integer i) {
        userExample.clear();;
        userExample.createCriteria().andRoleEqualTo(i);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }
}
