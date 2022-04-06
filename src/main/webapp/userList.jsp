<%--
  Created by IntelliJ IDEA.
  User: Seaaa
  Date: 2022/4/6
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@ page import="java.sql.ResultSet" %>
<h1>UserList</h1>
<table border="1">
    <tr>
        <td>Id</td><td>Username</td><td>password</td><td>Email</td><td>Gender</td><td>Birthday</td>
    </tr>
    <%
        //get rs from request attribute
        ResultSet rs = (ResultSet) request.getAttribute("rsname");//name of attribute -
        if(rs==null){
    %>
    <tr>
        <td>No Data!!!</td>
    </tr>
    <%}else{
            while(rs.next()){
                //get one by one
                out.println("<tr>");
                out.println("<td>"+rs.getInt("id")+"</td>");
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td>"+rs.getString("password")+"</td>");
                out.println("<td>"+rs.getString("email")+"</td>");
                out.println("<td>"+rs.getString("gender")+"</td>");
                out.println("<td>"+rs.getString("birthdate")+"</td>");
                out.println("<tr>");
            }
    }

    %>
</table>
<%@include file="footer.jsp"%>



