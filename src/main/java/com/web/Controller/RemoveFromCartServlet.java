package com.web.Controller;

import com.web.Entity.CartItem;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.CartServiceImpl;
import com.web.ServiceInterface.CartServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/removeFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {

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
        Long userId = (Long) request.getSession().getAttribute("userId");
        System.out.println(bookId+" "+userId);

        boolean success = cartService.removeBookFromCart(userId, bookId);

        // Load the updated cart items back into the session
        if (success) {
            List<CartItem> updatedCartItems = cartService.getCartItems(userId)
                .stream()
                .map(CartItem::new)
                .collect(Collectors.toList());
            request.getSession().setAttribute("cartItems", updatedCartItems); // Update the session with new cart items
            
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Book successfully removed from cart!");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to remove book from cart.");
        }
    }

}
