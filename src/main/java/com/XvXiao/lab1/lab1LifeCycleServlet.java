package com.XvXiao.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lab1LifeCycleServlet", value = "/lab1Life")
public class lab1LifeCycleServlet extends HttpServlet {
    int time=0;
    public lab1LifeCycleServlet(){
        System.out.println("I Am from default constructor");
        System.out.println("2020211001001106 XvXiao");
    }
    @Override
    public void init() throws ServletException {
        time++;
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("service");
        time++;
        PrintWriter out=response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Since loading, this servlet</h2>");
        out.println("<h2>has been access "+time+" times.</h2>");
        out.println("<h2>2020211001001106 XvXiao</h2>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        time++;
        System.out.println("service");
    }

    @Override
    public void destroy() {
        time++;
        System.out.println("destroy");
    }
}
