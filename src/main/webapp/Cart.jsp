<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Entity.CartItem" %>
<%@ page import="com.web.Entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/cart.css">
    <script>
        function removeFromCart(bookId) {
            if (confirm("Are you sure you want to remove this item from the cart?")) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "removeFromCartServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            location.reload(); // Reload the page to reflect changes
                        } else {
                            alert("Failed to remove item from cart. Please try again.");
                        }
                    }
                };
                xhr.send("bookId=" + encodeURIComponent(bookId));
            }
        }
    </script>
</head>
<body>
<!-- Header -->
<div class="header">
    <div class="left">
        <button onclick="location.href='cart.jsp';">🛒 Cart</button>
    </div>
    <div class="right">
        <button onclick="location.href='buyerDashboard.jsp';">Back to Dashboard</button>
        <button onclick="location.href='placeOrder';">Buy</button>
    </div>
</div>
<!-- Cart Items -->
<div class="cart-container">
    <%
    List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
    if (cartItems == null || cartItems.isEmpty()) {
    %>
        <p>Your cart is empty.</p>
    <%
    } else {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            int quantity = cartItem.getQuantity();
            double price = book.getPrice();
            total += price * quantity; // Calculate total based on quantity
    %>
        <div class="cart-item">
            <img src="<%= book.getCoverImageUrl() %>" alt="<%= book.getTitle() %>">
            <div class="cart-details">
                <h3><%= book.getTitle() %></h3>
                <p>Author: <%= book.getAuthor() %></p>
                <p>Price: $<%= price %> x <%= quantity %> = $<%= price * quantity %></p>
            </div>
            <button class="remove-button" onclick="removeFromCart('<%= book.getId() %>')">Remove</button>
        </div>
    <%
        }
    %>
        <div class="total">Total: $<%= total %></div>
    <%
    }
    %>
</div>
</body>
</html>