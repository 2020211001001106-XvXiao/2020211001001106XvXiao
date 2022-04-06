package com.XvXiao.week6;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//tell tomcat this class is a listener class -how?
@WebListener
public class JDBCServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce){
        //like a main() method for this web app
        //called when tomcat start
        //use this method to create jdbc connection when tomcat start
        ServletContext context=sce.getServletContext();
        String driver=context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");

        //now use 4 variables to get connection
        try {
            Class.forName(driver);
            Connection con= DriverManager.getConnection(url,username,password);
            System.out.println("i am contextInitialized()-- >"+con);//when?
            //System.out.println("Connection --> in Register "+con);//just print for test

            //set connection as a context attribute -- for all jsp and servlet context attribute
            context.setAttribute("con",con);
        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //end point of web app
        //this method called when tomcat stop
        System.out.println("i am in contextDestroyed()");//when this line print?
        //remove attribute connection from context
        sce.getServletContext().removeAttribute("con");//name
    }
}
