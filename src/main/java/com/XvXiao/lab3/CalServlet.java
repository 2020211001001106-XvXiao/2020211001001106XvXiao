package com.XvXiao.lab3;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.org.apache.bcel.internal.generic.DCONST;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalServlet", value = "/Lab3/CalServlet")
public class CalServlet extends HttpServlet {
    //todo 1:create a method to add(int firstVal, int secondVal) two number
    private int Add(int firstVal,int secondVal) {
        return firstVal+secondVal;
    }
    //todo 2:create a method to subtract two number
    private int Subtract(int firstVal,int secondVal){
        return firstVal - secondVal;
    }
    //todo 3:create a method to multiply two number
    private int Multiply(int firstVal,int secondVal){
        return firstVal * secondVal;
    }
    //todo 4:create a method to divide two number
    private double Divide(int firstVal,int secondVal){
        double  NewfirstVal = Double.parseDouble(String.valueOf(firstVal));
        double newsecondVal =  Double.parseDouble(String.valueOf(secondVal));
        return NewfirstVal / newsecondVal;
    }
    //todo 5:create a method to computedLength of a String
    private String computedLength(String name){

        return Integer.toString(name.length());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("666");
        //todo 6: get all request parameters- firstValue , secondValue,name,action
        int firstValue= Integer.parseInt(request.getParameter("FirstValue"));
        System.out.println("999");
        int secondValue= Integer.parseInt(request.getParameter("SecondValue"));
        System.out.println("777");
        String name=request.getParameter("Name");
        System.out.println("888");
        String action = request.getParameter("action");
        System.out.println(action);
        //todo 7: use if else to determine action is add or subtract otr multiply or divide or computerLength
        //todo 8: call method ad,subtract,multiply,divice or computeLength based on action and get the calculated result
        if(action.equals("Add")){
           int result= Add(firstValue,secondValue);
           System.out.println(firstValue+"+"+secondValue+"="+result);
        }
        else if (action.equals("Subtract")){
            int result= Subtract(firstValue,secondValue);
            System.out.println(firstValue+"-"+secondValue+"="+result);
        }
        else if(action.equals("Multiply")){
            int result= Multiply(firstValue,secondValue);
            System.out.println(firstValue+"*"+secondValue+"="+result);
        }
        else if(action.equals("Divide")){
            double result= Divide(firstValue,secondValue);
            System.out.println(firstValue+"/"+secondValue+"="+result);
        }else {
            String result= computedLength(name);
            System.out.println("computedLength="+result);
        }


        //todo 12: if action = computeLength
        if(action.equals("Compute Length")){
            name = request.getParameter("Name");
            //todo 13:create 2 cookies name cName,cLength and set the value
            Cookie cName = new Cookie("cName",name);
            Cookie cLength = new Cookie("cLength",computedLength(name));
            cName.setMaxAge(50);
            cLength.setMaxAge(50);
            //todo 14:add 2 cookies into response
            response.addCookie(cName);
            response.addCookie(cLength);
            System.out.println(name.length());
        }
        else {
            //todo 9: if action = add or  subtract or multiply or divide
            //todo 10: crate 3 cookies name cFirstValue , cSecondValue,cResult and set the value of cookie
            Cookie cFirstValue = new Cookie("cFirstValue",Integer.toString(firstValue));
            Cookie cSecondValue = new Cookie("cSecondValue",Integer.toString(secondValue));
            Cookie cResult = null;
            if (action.equals("Add")) {
                int result = Add(firstValue, secondValue);
                cResult = new Cookie("cResult",Integer.toString(result));
                System.out.println(result);
            } else if (action.equals("Subtract")) {
                int result = Subtract(firstValue, secondValue);
                cResult = new Cookie("cResult",Integer.toString(result));
                System.out.println(result);
            } else if (action.equals("Multiply")) {
                int result = Multiply(firstValue, secondValue);
                cResult = new Cookie("cResult",Integer.toString(result));
                System.out.println(result);
            } else if (action.equals("Divide")) {
                double result = Divide(firstValue, secondValue);
                cResult = new Cookie("cResult",Double.toString(result));
                System.out.println(result);
            }
            cFirstValue.setMaxAge(50);
            cSecondValue.setMaxAge(50);
            //todo 11: add 3 cookies into response
            if(cResult!=null) {
                cResult.setMaxAge(50);
                response.addCookie(cResult);
            }
            response.addCookie(cFirstValue);
            System.out.println(firstValue);
            response.addCookie(cSecondValue);
            System.out.println(secondValue);
        }
        //todo 15:send redirect to cal.jsp
        response.sendRedirect("cal.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}