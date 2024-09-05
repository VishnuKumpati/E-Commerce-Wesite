package com.web.Controller;

import com.web.Entity.CartItem;
import com.web.Service.serviceImpl.CartServiceImpl;
import com.web.ServiceInterface.CartServiceInterface;
import com.web.GenericConfig.GenericConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/getCartItems")
public class GetCartItemsServlet extends HttpServlet {
    private CartServiceInterface cartService;

    @Override
    public void init() throws ServletException {
        this.cartService = GenericConfiguration.createInstance(CartServiceImpl.class); // Initialize the CartService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session, do not create new one

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
            return;
        }

        Long buyerId = (Long) session.getAttribute("userId");

        try {
            List<CartItem> cartItems = cartService.getCartItems(buyerId)
                .stream()
                .map(CartItem::new)
                .collect(Collectors.toList());

            System.out.println("Retrieved Cart Items: " + cartItems);

            session.setAttribute("cartItems", cartItems); // Store list of CartItem in session

            // Forward the request to your JSP page that displays the cart
            request.getRequestDispatcher("Cart.jsp").forward(request, response); // Adjust the path as necessary

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page in case of failure
        }
    }
}
