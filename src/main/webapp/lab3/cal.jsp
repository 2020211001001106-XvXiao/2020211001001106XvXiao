<%@ page import="sun.security.util.Length" %><%--
  Created by IntelliJ IDEA.
  User: Seaaa
  Date: 2022/6/1
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    String FirstValue="";
    String SecondValue="";
    String Result="";
    String Name = "";
    String Length = "";
    if (cookies!=null){
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("cFirstValue")){
                FirstValue=cookie.getValue();
            }
            if (cookie.getName().equals("cSecondValue")){
                SecondValue=cookie.getValue();
            }
            if (cookie.getName().equals("cResult")){
                Result=cookie.getValue();
            }
            if (cookie.getName().equals("cName")){
                Name=cookie.getValue();
            }
            if (cookie.getName().equals("cLength")){
                Length=cookie.getValue();
            }
        }
    }
%>
<form method="post" id="form" action="CalServlet">
    <table>
    <tr>
        <td>
            First val: <input id="FirstValue" name="FirstValue" type="text" value="<%=FirstValue%>">
        </td>
        <td>
            Enter a name: <input id="Name" name="Name" type="text" value="<%=Name%>">
        </td>
    </tr>
    <tr>
        <td>
            Second val: <input id="SecondValue" name="SecondValue" type="text" value="<%=SecondValue%>">
        </td>
        <td>
            Length: <input id="Length" name="Length" type="text" value="<%=Length%>">
        </td>
    </tr>
    <tr>
        <td>
            Result: <input id="Result" name="Result" type="number" value="<%=Result%>">
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" name="action" value="Add" onclick="verifyNumber('Add')">
            <input type="submit" name="action" value="Subtract" onclick="verifyNumber('Subtract')">
            <input type="submit" name="action" value="Multiply" onclick="verifyNumber('Multiply')">
            <input type="submit" name="action" value="Divide" onclick="verifyNumber('Divide')">
            <input type="submit" name="action" value="Compute Length" onclick="verifyNumber('Compute Length')">
            <input type="Button" value="Reset">
        </td>

    </tr>
    </table>



</form>
</body>
</html>
<script>
    function  verifyNumber(action){
        let FirstValue = document.getElementById("FirstValue").value;
        let SecondValue = document.getElementById("SecondValue").value;
        let name = document.getElementById("Name").value;
        if(action==="Divide" && SecondValue === 0)
        {
            alert('The denominator cant be 0!!');
            return false;
        }
        if(FirstValue === ''&&action!=="Compute Length"){
            alert('First Value  is NULL!!');
            return;
        }
        if(SecondValue === ''&&action!=="Compute Length"){
            alert('Second Value is NULL !!');
            return;
        }
        FirstValue = Number(FirstValue)
        SecondValue = Number(SecondValue)
        if(!Number.isFinite(FirstValue)){
            alert('First Value is not a Number!!');
            return false;
        }
        if(!Number.isFinite(SecondValue)){
            alert('Second Value is not a Number!!');
            return false;
        }

    }
</script>