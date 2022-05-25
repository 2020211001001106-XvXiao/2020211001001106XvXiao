package com.XvXiao.week5;

import com.XvXiao.dao.UserDao;
import com.XvXiao.model.User;

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
        //when user click login menu - request is get
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //now move jdbc code in dao - MVC design
        //write mvc code
        //use model and dao

        try {
            UserDao userDao=new UserDao();
            User user= userDao.findByUsernamePassword(con,username,password);//this method use for login
            if (user!=null){
                //valid
                //set user into request
                //week 8 code - demo #1- use cookies for session
                //add code for remember me
                String rememberMe=request.getParameter("rememberMe");//1=checked,null if not checked
                if(rememberMe!=null && rememberMe.equals("1")){
                    //want to remember me
                    //create 3 cookies
                    Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie=new Cookie("cRememberMe",rememberMe);
                    //set age of cookies
                    usernameCookie.setMaxAge(60*60*24*15);//5 sec - test --- 15 days = 60*60*24*15
                    passwordCookie.setMaxAge(60*60*24*15);
                    rememberMeCookie.setMaxAge(60*60*24*15);
                    //add 3 cookies into response
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }

                //create cookie
                //step 1:create an object of cookie class
                //Cookie c = new Cookie("sessionid",""+user.getId());
                //step 2:set age of cookie
                //c.setMaxAge(10*60);//in sec- 10 min - 7 days - 7*24*60*60
                //step3:add cookie into response
                 //   response.addCookie(c);
              HttpSession session= request.getSession();//create a new session if session doesnot exist - -otherwise return existing session
                //check session id
                System.out.println("session id -->"+session.getId());//session id
                //set time for session
                session.setMaxInactiveInterval(10);//for 5 10 section if request not come in - tomcat kill session -set 60*60 ==1 h

                //set user model into request
                //week 8 0- change request(one page) to session - so we can get session attribute in many jsp page
                session.setAttribute("user",user);//get user info in jsp
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else {
                //invalid
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //forward - JSP

//        try {
//            String sql="Select * from usertable where username=? and password=? ";
//            PreparedStatement preparedStatement=con.prepareStatement(sql);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(2,password);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            PrintWriter out=response.getWriter();
//            if (resultSet.next()){
////                out.println("<html><body>");
////                out.println("<h1>");
////                out.println("Login Success!!!");
////                out.println("</h1>");
////                out.println("<h1>");
////                out.println("Welcome,"+username);
////                out.println("</h1>");
////                out.println("</body></html>");
//                //get from rs and set into request attribute
//                request.setAttribute("username", resultSet.getInt("id"));
//                request.setAttribute("username", resultSet.getString("username"));
//                request.setAttribute("password", resultSet.getString("password"));
//                request.setAttribute("email", resultSet.getString("email"));
//                request.setAttribute("gender", resultSet.getString("gender"));
//                request.setAttribute("birthDate", resultSet.getString("birthDate"));
//                //forward to userinfo.jsp
//                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
//            }else {
////                out.println("<html><body>");
////                out.println("<h1>Username or Password Error!!!</h1>");
////                out.println("</body></html>");
//                  request.setAttribute("message","Username or Password Error!!!");
//                  request.getRequestDispatcher("login.jsp").forward(request,response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void destroy() {
        super.destroy();
        //close connection here - when stop tomcat
    }
}
