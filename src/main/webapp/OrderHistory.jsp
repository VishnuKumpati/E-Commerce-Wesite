<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Entity.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="css/orderHistory.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .status {
            font-weight: bold;
        }
        .completed {
            color: #28a745;
        }
        .pending {
            color: #ffc107;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Order History</h1>
        <%
            List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");
            if (orderHistory == null || orderHistory.isEmpty()) {
        %>
            <p>No orders found.</p>
        <%
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product Name</th>
                        <th>Author</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Order Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Order order : orderHistory) {
                    %>
                    <tr>
                        <td><%= order.getId() %></td>
                        <td><%= order.getTitle() %></td>
                        <td><%= order.getAuthor() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td>$<%= order.getTotalPrice() %></td>
                        <td><%= order.getOrderDate() %></td>
                        <td class="status <%= "pending" %>">
                            Pending
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            }
        %>
    </div>
</body>
</html>
