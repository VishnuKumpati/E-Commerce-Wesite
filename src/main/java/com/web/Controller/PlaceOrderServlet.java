package com.web.Controller;

import com.web.Entity.Book;
import com.web.Entity.CartItem;
import com.web.GenericConfig.DBconfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection con;

    public PlaceOrderServlet() {
        // Initialize database connection
        DBconfiguration dbconfig = new DBconfiguration();
        con = dbconfig.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        Long userId = (Long) session.getAttribute("userId");
        double totalPrice = 0;

        // Log cart items for debugging
        System.out.println("Cart Items: " + cartItems);
        System.out.println("User ID: " + userId);

        if (cartItems != null && !cartItems.isEmpty()) {
            try {
                con.setAutoCommit(false); // Start transaction
                System.out.println("Starting transaction for user ID: " + userId);

                for (CartItem cartItem : cartItems) {
                    Book book = cartItem.getBook();
                    int quantity = cartItem.getQuantity(); // Assuming CartItem has a method to get quantity
                    System.out.println("Processing book ID: " + book.getId() + " with quantity: " + quantity);

                    String checkBookSql = "SELECT retailerId, price FROM book WHERE id = ?";
                    try (PreparedStatement checkStmt = con.prepareStatement(checkBookSql)) {
                        checkStmt.setLong(1, book.getId());
                        try (ResultSet rs = checkStmt.executeQuery()) {
                            if (rs.next()) {
                                long retailerId = rs.getLong("retailerId");
                                double price = rs.getDouble("price");
                                double itemTotalPrice = price * quantity;

                                // Insert into orders table
                                placeOrder(userId, book.getId(), retailerId, quantity, itemTotalPrice);
                                totalPrice += itemTotalPrice; // Accumulate total price
                                System.out.println("Inserted order for book ID: " + book.getId() + " with total price: " + itemTotalPrice);
                            } else {
                                System.err.println("No retailer found for book ID: " + book.getId());
                            }
                        }
                    }
                }

                con.commit();
                System.out.println("Transaction committed successfully for user ID: " + userId);
                session.removeAttribute("cartItems"); // Clear cart items
                request.setAttribute("message", "Order placed successfully!");
                request.setAttribute("messageType", "success");
            } catch (SQLException e) {
                System.err.println("Error during order placement: " + e.getMessage());
                try {
                    con.rollback(); // Rollback in case of an error
                    System.err.println("Transaction rolled back due to an error.");
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
                throw new ServletException("Failed to place the order.", e);
            }
        } else {
            request.setAttribute("message", "Your cart is empty.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("orderPlaced.jsp").forward(request, response);
    }

    private void placeOrder( Long userId, Long bookId, long retailerId, int quantity, double totalPrice) throws SQLException {
        String sql = "INSERT INTO orders (buyer_id, book_id, retailer_id, quantity, total_price, order_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, bookId);
            stmt.setLong(3, retailerId);
            stmt.setInt(4, quantity);
            stmt.setDouble(5, totalPrice);
            stmt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order inserted into database. Rows affected: " + rowsAffected);
            } else {
                throw new SQLException("Failed to insert order into database.");
            }
        }
    }

    @Override
    public void destroy() {
        // Clean up connection
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.destroy();
    }
}
