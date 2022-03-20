package com.XvXiao.week4;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(
        name = "ConfigDemoServlet",
        urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name = "name",value = "XvXiao"),
                @WebInitParam(name = "studentId",value = "2020211001001106")
        }
)
public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = getServletConfig().getInitParameter("name");
        String studentID = getServletConfig().getInitParameter("studentId");
        PrintWriter writer=response.getWriter();
        writer.println("name : "+name);
        writer.println("studentId : "+studentID);
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
