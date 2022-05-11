<%--
  Created by IntelliJ IDEA.
  User: Seaaa
  Date: 2022/5/10
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo 1- Week 11</title>
</head>
<body>
<%
    //SET ATTRIBUTE
    pageContext.setAttribute("attName","att in page");
    request.setAttribute("attName","att in request");

    session.setAttribute("attName","att in session");
    application.setAttribute("attName","Att in application");
%>
<h2> get 4 scope variable - using java code </h2>
att value :<%=pageContext.getAttribute("attName",PageContext.PAGE_SCOPE)%><br>
att value :<%=pageContext.getAttribute("attName",PageContext.REQUEST_SCOPE)%><br>
att value :<%=pageContext.getAttribute("attName",PageContext.SESSION_SCOPE)%><br>
att value :<%=pageContext.getAttribute("attName",PageContext.APPLICATION_SCOPE)%><br>

Find att : <%=pageContext.findAttribute("attName")%><!--which one ?-->

<h2> get 4 scope variable - using El code </h2>

Att value : ${attName}<!-- ==findAttribute(“attName”) which one -->
<h2>not find - get Attribute from session</h2>
att value : ${sessionScope.attName}<br><!--pageContext.getAttribute("attName",PageContext.SESSION_SCOPE)-->
att value : ${requestScope.attName}<br>
att value : ${pageScope.attName}<br>
att value : ${applicationScope.attName}<br>
</body>
</html>
