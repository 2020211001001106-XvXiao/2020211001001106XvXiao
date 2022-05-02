package com.XvXiao.week3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    Connection con=null;//class variable
    @Override
    public void init() throws ServletException {
        super.init();
        //way 2 - create connection with init()
        //to get connection we need 4 variables
        //connect to sql server
        //String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//name = value
        //String  url="jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false";
        //String username="sa";
        //String password="sdlkbldbl..";

        //there 4 variables should not be in java code -- must be in web.xml as config param
        //get init param
        //step 1- get servlet config
//        ServletConfig config=getServletConfig();
        //step 2 - get param
//        String driver=config.getInitParameter("driver");//<param-name>driver</param-name>
//        String url=config.getInitParameter("url");//<param-name>url</param-name>
//        String username=config.getInitParameter("username");//<param-name>username</param-name>
//        String password=config.getInitParameter("password");//<param-name>password</param-name>

        //demo #3 - use Servletcontext
//        ServletContext context=getServletContext();
//        String driver=context.getInitParameter("driver");
//        String url=context.getInitParameter("url");
//        String username=context.getInitParameter("username");
//        String password=context.getInitParameter("password");
//
//        //now use 4 variables to get connection
//        try {
//            Class.forName(driver);
//            con= DriverManager.getConnection(url,username,password);
//            System.out.println("Connection --> in Register "+con);//just print for test
//
//            //one connection
//        } catch (ClassNotFoundException| SQLException e) {
//            e.printStackTrace();
//        }
        con=(Connection) getServletContext().getAttribute("con");//name of attribute
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");

//        PrintWriter writer = response.getWriter();
//        writer.println("<br>username :"+username);
//        writer.println("<br>password :"+password);
//        writer.println("<br>email :"+email);
//        writer.println("<br>gender :"+gender);
//        writer.println("<br>birth Date :"+birthDate);
//        writer.close();


        try {
            Statement st=con.createStatement();
            String sql="insert into usertable(username,password,email,gender,birthdate)"+
                    "values('"+username+"','"+password+"','"+email+"','"+gender+"','"+birthDate+"')";
            System.out.println("sql"+sql);
            int n=st.executeUpdate(sql);
            System.out.println("n-->"+n);//==1 succes--insert

            //get data and print data
            //PrintWriter out=response.getWriter();
            //sql="select id,username,password,email,gender,birthdate from usertable";
            //ResultSet resultSet=st.executeQuery(sql);
//            out.println("<html><body>");
//            out.println("<table border='2'>");
//            out.println("<tr><th>ID</th><th>username</th><th>password</th><th>email</th><th>gender</th><th>birthDate</th></tr>");
//            out.println("<tr><th>"+1+"</th><th>"+username+"</th><th>"+password+"</th><th>"+email+"</th><th>"+gender+"</th><th>"+birthDate+"</th></tr>");
//            out.println("</table>");
//            out.println("</body></html>");

            //user request attribute
            //set rs into request attribute
            //request.setAttribute("rsname",resultSet);//name- string value- anytype(object)

            //request.getRequestDispatcher("userList.jsp").forward(request,response);//at this point request given to userList.jsp
            //no more here
            //URL doesn't change
            //System.out.println("i am in RegisterServlet-->doPost-->after forward()");//no see this line
            //after register user can login


            response.sendRedirect("login");//LoginServlet
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
