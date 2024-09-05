package com.web.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.Entity.Retailer;
import com.web.Service.serviceImpl.AdminServiceImpl;
import com.web.ServiceInterface.AdminServiceInterface;
@WebServlet("/viewRetailers")
public class ViewAllRetailer extends HttpServlet {
    private AdminServiceInterface service;

    public ViewAllRetailer() {
        service = new AdminServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manageAction = request.getParameter("manageAction");
        List<Retailer> retailers = service.getAllRetailers();
        
        
        if (manageAction != null && manageAction.equals("manageRetailers")) {
            // If manage action is 'manageRetailers', forward to manageRetailers.jsp
        	request.setAttribute("retailers", retailers);
            request.getRequestDispatcher("manageRetailers.jsp").forward(request, response);
        } else {
            // Default action is to view all retailers
        	request.setAttribute("retailers", retailers);
            request.getRequestDispatcher("viewRetailers.jsp").forward(request, response);
        }
    }
}
