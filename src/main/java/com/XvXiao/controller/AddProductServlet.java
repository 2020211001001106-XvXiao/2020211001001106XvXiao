package com.XvXiao.controller;

import com.XvXiao.dao.ProductDao;
import com.XvXiao.model.Category;
import com.XvXiao.model.Product;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import sun.security.mscapi.CPublicKey;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con=null;
    public void init(){
       con=(Connection) getServletContext().getAttribute("con");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList= Category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
            //we will use latter
            String path="/WEB-INF/views/admin/addProduct.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get all prameters
        String productName=request.getParameter("productName");
        double price=request.getParameter("price")!=null? Double.parseDouble(request.getParameter("price")):0.0;//if null error
        int categoryId=request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):8;
        String productDescription = request.getParameter("productDescription");

        //get picture
        InputStream inputStream=null;
        Part filePart=request.getPart("picture");//baidu
        if(filePart!=null){
            inputStream=filePart.getInputStream();
        }
        //set into model
        Product product=new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setPrice(price);
        product.setCategoryId(categoryId);

        //call same in dao
        ProductDao productDao=new ProductDao();
        try {
            int n=productDao.save(product,con);
            //redirect
            if (1>0)
                response.sendRedirect("productList");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
