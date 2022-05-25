package com.XvXiao.controller;

import com.XvXiao.dao.UserDao;
import com.XvXiao.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con=null;//class variable
    @Override
    public void init() throws ServletException {
        super.init();
        con=(Connection) getServletContext().getAttribute("con");//name of attribute
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //write code
        //ToDo 1:forward to WEB-INF/views/updateUser.jsp
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
        //ToDo 2:create one jsp page - update User

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //write code to update user info- can update password,email,gender,birthDate

        //ToDo 1:get all (6) request parameters
        int id= Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");
        //ToDo 2:create an object of User Model
        User user=new User();
        //ToDo 3:set all 6 request parameters values into User model - setXXX()
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthDate(birthDate);
        //ToDo 4:create an object of UserDao
        UserDao userDao=new UserDao();
        //ToDo 5:call updateUser() in UserDao
        try {
            userDao.updateUser(con,user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ToDo 6:forward to WEB-INF/views/userInfo.jsp
        HttpSession session= request.getSession();
        session.setMaxInactiveInterval(10);
        session.setAttribute("user",user);
        request.getRequestDispatcher("accountDetails").forward(request,response);
    }
}
