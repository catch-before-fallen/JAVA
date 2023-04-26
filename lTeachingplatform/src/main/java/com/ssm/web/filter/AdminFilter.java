package com.ssm.web.filter;

import com.ssm.web.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            //过滤非教师用户访问后台



        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        Object obj = session.getAttribute("curruser");

        if(obj instanceof User){
            System.out.println("管理员正常登录！");
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            System.out.println("游客访问后台请求--已被过滤");
            HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
            httpServletResponse.sendError(405,"未登录管理员账号进行后台访问操作！");
        }
    }

    @Override
    public void destroy() {

    }
}
