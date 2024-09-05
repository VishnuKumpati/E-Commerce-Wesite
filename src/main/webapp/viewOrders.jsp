<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Entity.Order" %> <!-- Adjust the package path as per your actual project structure -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Orders</title>
</head>
<body>
    <h1>Orders List</h1>

    <%
        String message = (String) request.getAttribute("message");
        String messageType = (String) request.getAttribute("messageType");
        if (message != null && !message.isEmpty()) {
    %>
        <div class="<%= messageType %>">
            <%= message %>
        </div>
    <%
        }
    %>

    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Buyer ID</th>
                <th>Book Title</th>
                <th>Author</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Order Date</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Retrieve the list of orders from the request attribute
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                if (orders != null) {
                    for (Order order : orders) {
            %>
                        <tr>
                            <td><%= order.getId() %></td>
                            <td><%= order.getBuyerId() %></td>
                            <td><%= order.getTitle() %></td>
                            <td><%= order.getAuthor() %></td>
                            <td><%= order.getQuantity() %></td>
                            <td><%= order.getTotalPrice() %></td>
                            <td><%= order.getOrderDate() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="7">No orders found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
