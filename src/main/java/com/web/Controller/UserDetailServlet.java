package com.web.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.Entity.Buyer;
import com.web.Service.serviceImpl.AdminServiceImpl;
import com.web.ServiceInterface.AdminServiceInterface;

@WebServlet("/userDetails")
public class UserDetailServlet extends HttpServlet {
    private AdminServiceInterface service;

    public UserDetailServlet() {
        service = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Long userId = Long.parseLong(request.getParameter("userId")); // Match the parameter name with JSP

        System.out.println("userId : " + userId);

        Buyer buyer = service.getUserDetails(userId); // Ensure this method exists in your service and returns a Buyer
        System.out.println("buyer from setvlet: "+buyer);
        
        // Set the user details as a request attribute
        request.setAttribute("user", buyer);
        
        // Forward the request to the ManageUsers.jsp page to display the user details
        request.getRequestDispatcher("ManageUsers.jsp").forward(request, response);
    }
}
