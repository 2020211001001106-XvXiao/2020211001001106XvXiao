package com.XvXiao.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //request come here - before go to servlet -doGet() or doPost()
        System.out.println("i am in AuthFilter-->doFilter()- before servlet-(request come here)");//when called?
        chain.doFilter(request, response);//call next filter- if no next filter - then go to servlet
        //response after servlet come back here
        System.out.println("i am in AuthFilter-->doFilter()- After servlet (response come here)");//when called?
    }
}
