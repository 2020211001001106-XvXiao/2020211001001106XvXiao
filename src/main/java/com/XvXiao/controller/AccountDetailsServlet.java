package com.XvXiao.controller;

import com.XvXiao.dao.OrderDao;
import com.XvXiao.model.Order;
import com.XvXiao.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "AccountDetailsServlet", value = "/accountDetails")
public class AccountDetailsServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        super.init();
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if (session!=null&&session.getAttribute("user")!=null){
            //user has logged in
            User user=(User) session.getAttribute("user");
            int userId=user.getId();

            request.setAttribute("user",user);
            OrderDao orderDao=new OrderDao();
            List<Order> orderList= orderDao.findByUserId(con,userId);//all order for a user
            request.setAttribute("orderList",orderList);
            String path="WEB-INF/views/accountDetails.jsp";
            request.getRequestDispatcher(path).forward(request,response);

        }else {
            //ask for login
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
