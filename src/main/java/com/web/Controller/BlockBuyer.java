package com.web.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.AdminServiceImpl;
import com.web.ServiceInterface.AdminServiceInterface;
@WebServlet("/block")
public class BlockBuyer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminServiceInterface service;

    public BlockBuyer() {
        service = GenericConfiguration.createInstance(AdminServiceImpl.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the buyer ID to be blocked from the request
        Long buyerId = Long.parseLong(request.getParameter("userId"));
        System.out.println(buyerId);

       String status=service.getStatus(buyerId);
       System.out.println(status);
       if(status.equals("active")) {
        boolean success = service.blockBuyer(buyerId);
        System.out.println(success);
       
        if (success) {
            
            response.sendRedirect("BlockedSuccessfully.jsp");
        } else {
          
            response.sendRedirect("ErrorPage.jsp");
        }
       
       }
       else {
       	  response.sendRedirect("AlreadyBlocked.jsp");
       }
    }
}
