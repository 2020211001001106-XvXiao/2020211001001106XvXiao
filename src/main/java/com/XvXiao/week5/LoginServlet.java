package com.XvXiao.week5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con=null;//class variable
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");
        //now use 4 variables to get connection
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection --> in LoginServlet "+con);//just print for test

            //one connection
        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        try {
            String sql="Select * from usertable where username=? and password=? ";
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            PrintWriter out=response.getWriter();
            if (resultSet.next()){
                out.println("<html><body>");
                out.println("<h1>");
                out.println("Login Success!!!");
                out.println("</h1>");
                out.println("<h1>");
                out.println("Welcome,"+username);
                out.println("</h1>");
                out.println("</body></html>");
            }else {
                out.println("<html><body>");
                out.println("<h1>Username or Password Error!!!</h1>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        //close connection here - when stop tomcat
    }
}
