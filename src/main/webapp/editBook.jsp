<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.web.Entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <link rel="stylesheet" href="css/editBook.css">
    <style>
        
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Edit Book</h2>
        <form action="EditBookServlet" method="post">
            <input type="hidden" name="id" value="<%= request.getAttribute("bookId") %>">
            <input type="hidden" name="coverImageUrl" value="<%= request.getAttribute("coverImageUrl") %>">

            <!-- Image preview -->
            <div class="image-preview">
                <img src="<%= request.getAttribute("coverImageUrl") %>" alt="Book Cover">
            </div>

            <div>
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="<%= request.getAttribute("title") %>" required>
            </div>
            <div>
                <label for="author">Author:</label>
                <input type="text" id="author" name="author" value="<%= request.getAttribute("author") %>" required>
            </div>
            <div>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= request.getAttribute("price") %>" required>
            </div>
            <div>
                <label for="stockQuantity">Stock Quantity:</label>
                <input type="text" id="stockQuantity" name="stockQuantity" value="<%= request.getAttribute("stockQuantity") %>" required>
            </div>
            <button type="submit">Update Book</button>
        </form>
    </div>
</body>
</html>
