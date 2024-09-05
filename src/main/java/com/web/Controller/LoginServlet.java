package com.web.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.ServiceInterface.RegisterServiceInterface;
import com.web.Entity.Admin;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.RegisterServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisterServiceInterface service;

    public LoginServlet() {
        service = GenericConfiguration.createInstance(RegisterServiceImpl.class);
    }

    @SuppressWarnings("unused")
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Object user = service.authenticateUser(email, password);

        if ("blocked".equals(user)) {
            req.setAttribute("errorMessage", "Your account has been blocked. Please contact support.");
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, res);
        } else if (user != null) {
        	if (user == null) {
                
                req.setAttribute("errorMessage", "Invalid email or password.");
                req.getRequestDispatcher("login.jsp").forward(req, res);
                return;
            
        	}
            // Identify the type of user and redirect to the appropriate dashboard
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                req.getSession().setAttribute("user", admin);
                req.getSession().setAttribute("userName", admin.getName()); // Set user name
                req.getSession().setAttribute("userId", admin.getId()); // Store user ID
                res.sendRedirect("adminDashboard.jsp");
            } else if (user instanceof Buyer) {
                Buyer buyer = (Buyer) user;
                req.getSession().setAttribute("user", buyer);
                req.getSession().setAttribute("userName", buyer.getName()); // Set user name
                req.getSession().setAttribute("userId", buyer.getId()); // Store user ID
                res.sendRedirect("getAllBooksServlet");
               // res.sendRedirect("buyerDashboard.jsp");
            } else if (user instanceof Retailer) {
                Retailer retailer = (Retailer) user;
                req.getSession().setAttribute("user", retailer);
                req.getSession().setAttribute("userName", retailer.getName()); // Set user name
                req.getSession().setAttribute("userId", retailer.getId()); // Store user ID
                res.sendRedirect("retailerDashBoard.jsp");
            }

            // Continue with the login process
            // Set user session and redirect to appropriate dashboard
        } else {
            req.setAttribute("errorMessage", "Invalid email or password.");
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, res);
        }
    }

}
