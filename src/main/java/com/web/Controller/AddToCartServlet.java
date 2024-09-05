package com.web.Controller;

import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.CartServiceImpl;
import com.web.ServiceInterface.CartServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


    @WebServlet("/addToCartServlet")
    public class AddToCartServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;
        private CartServiceInterface cartService;

        @Override
        public void init() throws ServletException {
            cartService = GenericConfiguration.createInstance(CartServiceImpl.class);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String bookIdStr = request.getParameter("bookId");
            long bookId = Long.parseLong(bookIdStr);

            // Retrieve userId as Long
            Long userId = (Long) request.getSession().getAttribute("userId");
            System.out.println(bookId+" "+userId);

            // Pass the long userId directly to the service method
            boolean success = cartService.addBookToCart(userId, bookId);
           

            // Send back response
            response.setContentType("text/plain");
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Book successfully added to cart!");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to add book to cart.");
            }
        }}
    
