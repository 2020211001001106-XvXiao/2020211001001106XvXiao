package com.XvXiao.lab1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lab1MyDearServlet", value = "/lab1MyDear")
public class lab1MyDearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("666");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String submit=request.getParameter("submit");
        String myClass = request.getParameter("class");
        String id=request.getParameter("id");
        PrintWriter out=response.getWriter();
        out.println("<html><body>");
        out.println("<h1>This is lab1MyDearServlet.java</h1>");
        out.println("<h2>name: "+name+"</h2>");
        out.println("<h2>submit: "+submit+"</h2>");
        out.println("<h2>class: "+myClass+"</h2>");
        out.println("<h2>ID: "+id+"</h2>");
        out.println("<h2>2020211001001106 XvXiao</h2>");
        out.println("</body></html>");
    }
}
