package com.web.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.Entity.Buyer;
import com.web.Service.serviceImpl.AdminServiceImpl;
import com.web.ServiceInterface.AdminServiceInterface;

@WebServlet("/viewBuyers")
public class ViewBuyersServlet extends HttpServlet {
    private AdminServiceInterface service;

    public ViewBuyersServlet() {
        service = new AdminServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewAction = request.getParameter("viewAction");
        String manageAction = request.getParameter("manageAction");
       

        if (viewAction != null && viewAction.equals("viewUsers")) {
            List<Buyer> buyers = service.getAllBuyers();
            request.setAttribute("buyers", buyers);
            request.getRequestDispatcher("viewBuyers.jsp").forward(request, response);
        } else if (manageAction != null && manageAction.equals("manageUsers")) {
            List<Buyer> buyers = service.getAllBuyers();
            request.setAttribute("buyers", buyers);
            request.getRequestDispatcher("ManageUsers.jsp").forward(request, response);
        } else {
            // Handle default action, if needed
            // You can also redirect to an error page or other default action here
        }
    }
}
