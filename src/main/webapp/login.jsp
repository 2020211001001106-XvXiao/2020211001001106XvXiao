<%--
  Created by IntelliJ IDEA.
  User: Seaaa
  Date: 2022/3/27
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>Login</h1>
<form method="post" action="login">
    username<input type="text" name="username"/><br/>
    password<input type="password" name="password"/><br/>
    <input type="submit" value="Login"/>
</form>

<%@include file="footer.jsp"%>
