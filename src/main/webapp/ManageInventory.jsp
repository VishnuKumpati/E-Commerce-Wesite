<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Inventory</title>
    <link rel="stylesheet" href="css/inventory.css">
   
</head>
<body>
    <div class="header">
        <a href="logout">Logout</a>
        <a href="retailerDashBoard.jsp" class="back-button">Back to Dashboard</a>
    </div>

    <div class="book-list">
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            Long retailerId = (Long) request.getAttribute("retailerId");
            if (books != null && !books.isEmpty()) {
                for (Book book : books) {
        %>
        <div class="book-container">
            <div class="book-image">
                <img src="<%= book.getCoverImageUrl() %>" alt="<%= book.getTitle() %>">
            </div>
            <div class="book-details">
                <h3><%= book.getTitle() %></h3>
                <p><strong>Author:</strong> <%= book.getAuthor() %></p>
                <p><strong>Price:</strong> $<%= book.getPrice() %></p>
                <p><strong>Stock Quantity:</strong> <%= book.getStockQuantity() %></p>
            </div>
            <div class="book-actions">
                <a href="editBook.jsp?id=<%= book.getId() %>&title=<%= book.getTitle() %>&author=<%= book.getAuthor() %>&price=<%= book.getPrice() %>&stockQuantity=<%= book.getStockQuantity() %>&coverImageUrl=<%= book.getCoverImageUrl() %>"
                   class="edit-button">Edit</a>
                <a href="deleteBook?id=<%= book.getId() %>&retailerId=<%= retailerId %>" class="delete-button">Delete</a>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No books available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
