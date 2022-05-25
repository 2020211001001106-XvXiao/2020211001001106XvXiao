package com.XvXiao.lab2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",
urlPatterns = {"/lab2/validate.jsp","/lab2/welcome.jsp"})
/*todo 1: map this filter for - 1. /lab2/validate.jsp and /lab2/welcome.jsp */
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        /*todo 2: System out print i  am LoginFilter--init()>*/
        System.out.println("i am in LoginFilter--init()>");
    }

    public void destroy() {
        /*todo 3: System out print i  am LoginFilter--destroy()>*/
        System.out.println("i am in LoginFilter--destroy()>");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*todo 4: System out print i am in LoginFilter--doFilter()-- request before chain> */
        System.out.println("i am in LoginFilter--doFilter()-- request before chain>");
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        /*todo 6: if(session not new){  */
        if(!req.getSession().isNew()){
            /*todo 7: forward to lab2/welcome.jsp */
            String path="/lab2/welcome.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        }
        /*todo 8:else{redirect to login.jsp}*/
        else {
            String path="/lab2/login.jsp";
            res.sendRedirect(path);
        }
        chain.doFilter(request, response);

        /*todo 5: System out print i am in LoginFilter--doFilter()-- request after chain> */
        System.out.println("i am in LoginFilter--doFilter()-- request after chain>");


    }
}
