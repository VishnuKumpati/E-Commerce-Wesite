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
@WebServlet("/blockRetailer")
public class BlockRetailer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminServiceInterface service;

    public BlockRetailer() {
        service = GenericConfiguration.createInstance(AdminServiceImpl.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the buyer ID to be blocked from the request
    	// Retrieve the buyer ID to be blocked from the request
    	Long buyerId = Long.parseLong(request.getParameter("id"));

        
        System.out.println(buyerId);

       String status=service.getStatusRetailer(buyerId);
       System.out.println(status);
       if(status.equals("active")) {
        boolean success = service.blockRetailer(buyerId);
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
