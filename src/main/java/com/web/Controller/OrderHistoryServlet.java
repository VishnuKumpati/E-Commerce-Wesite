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

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderServiceInterface orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        System.out.println(userId);
        List<Order> orderHistory = orderService.getOrderHistoryByUserId(userId);

        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
    }
}
