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
//        ServletContext context=getServletContext();
//        String driver=context.getInitParameter("driver");
//        String url=context.getInitParameter("url");
//        String username=context.getInitParameter("username");
//        String password=context.getInitParameter("password");
//        //now use 4 variables to get connection
//        try {
//            Class.forName(driver);
//            con= DriverManager.getConnection(url,username,password);
//            System.out.println("Connection --> in LoginServlet "+con);//just print for test
//
//            //one connection
//        } catch (ClassNotFoundException| SQLException e) {
//            e.printStackTrace();
//        }
        //only one line
        con=(Connection) getServletContext().getAttribute("con");//check
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);//call doPost
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
//                out.println("<html><body>");
//                out.println("<h1>");
//                out.println("Login Success!!!");
//                out.println("</h1>");
//                out.println("<h1>");
//                out.println("Welcome,"+username);
//                out.println("</h1>");
//                out.println("</body></html>");
                //get from rs and set into request attribute
                request.setAttribute("username", resultSet.getInt("id"));
                request.setAttribute("username", resultSet.getString("username"));
                request.setAttribute("password", resultSet.getString("password"));
                request.setAttribute("email", resultSet.getString("email"));
                request.setAttribute("gender", resultSet.getString("gender"));
                request.setAttribute("birthDate", resultSet.getString("birthDate"));
                //forward to userinfo.jsp
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else {
//                out.println("<html><body>");
//                out.println("<h1>Username or Password Error!!!</h1>");
//                out.println("</body></html>");
                  request.setAttribute("message","Username or Password Error!!!");
                  request.getRequestDispatcher("login.jsp").forward(request,response);
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
