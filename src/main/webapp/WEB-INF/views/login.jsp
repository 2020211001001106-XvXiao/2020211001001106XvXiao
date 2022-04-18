<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Seaaa
  Date: 2022/3/27
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>Login</h1>
<%
    if (!(request.getAttribute("message")==null)){
        //error
        out.println("<h3>"+request.getAttribute("message")+"</h3>");
    }
%>
<%
    //read cookies
   Cookie[] allCookies= request.getCookies();//give all cookies
    String username="",password="",rememberMeVal="";
    if(allCookies!=null){
        //we read 3 cookies
        for (Cookie c:allCookies) {
            //get one bby one
            if(c.getName().equals("cUsername")){
                //get values of cookies
                username=c.getValue();
            }
            if(c.getName().equals("cPassword")){
                //get values of cookies
                password=c.getValue();
            }
            if(c.getName().equals("cRememberMe")){
                //get values of cookies
                rememberMeVal=c.getValue();
            }

        }
    }

%>
<form method="post" action="login">
    username<input type="text" name="username" value="<%=username%>"/><br/>
    password<input type="password" name="password" value="<%=password%>"/><br/>
    <input type="checkbox" name="rememberMe" value="1" <%=rememberMeVal.equals("1")?"checked":""%>/>RememberMe<br/>
    <input type="submit" value="Login"/>
</form>

<%@include file="footer.jsp"%>
