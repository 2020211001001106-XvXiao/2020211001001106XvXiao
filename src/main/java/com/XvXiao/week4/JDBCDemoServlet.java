package com.XvXiao.week4;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//now we can use another way - more easy
//@WebServlet(name="JDBCDemoServlet",urlPatterns = {"/jdbc"},
//        initParams = {
//            @WebInitParam(name="driver",value = "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
//                @WebInitParam(name="url",value = "jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false"),
//                @WebInitParam(name="username",value = "sa"),
//                @WebInitParam(name="password",value = "sdlkbldbl..")
//        },
//        loadOnStartup = 1
//
//)//done --use @Webservlet - no web.xml code

//demo #3 - many servlet need driver , url ,username, password to connect db
//so its better use servletcontext(one for all servlet)- in web.xml not servlet Config(only for this servlet)
@WebServlet(urlPatterns = {"/jdbc"},loadOnStartup = 1)//only url and load on startup
public class JDBCDemoServlet extends HttpServlet {
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
        ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");

        //now use 4 variables to get connection
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection --> in JDBCDemoServlet "+con);//just print for test

            //one connection
        } catch (ClassNotFoundException|SQLException  e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //way 1 create connection within doget


        //dont create con here - only use it do select insert update

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
        //close connection here - when stop tomcat
    }
}
