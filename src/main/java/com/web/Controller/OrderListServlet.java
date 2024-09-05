package com.web.Controller;

import com.web.Entity.Order;
import com.web.Service.serviceImpl.OrderServiceImpl;
import com.web.ServiceInterface.OrderServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewOrders")
public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderServiceInterface orderService;

    public OrderListServlet() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long retailerId = (Long) request.getSession().getAttribute("userId"); // Retrieve retailer ID from session

        System.out.println("Retailer ID from session: " + retailerId);

        if (retailerId != null) {
            try {
                // Fetch orders using retailer ID
                List<Order> orders = orderService.getOrdersByRetailer(retailerId); // Implement this method accordingly
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving orders.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Retailer ID is required.");
        }
    }
}
