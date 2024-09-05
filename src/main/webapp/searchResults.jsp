<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Entity.Book" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Buyer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/buyer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background: #007bff;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header .left div {
            font-size: 18px;
        }

        .header .right {
            display: flex;
            align-items: center;
        }

        .header button,
        .search-bar button {
            background: transparent;
            border: none;
            color: white;
            margin-left: 15px;
            font-size: 16px;
            cursor: pointer;
            transition: color 0.3s;
        }

        .header button:hover {
            color: #dcdcdc;
        }

        .search-bar {
            display: none;
            position: absolute;
            top: 40px;
            left: 20px;
            background: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .search-bar input {
            padding: 10px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            width: 200px;
        }

        .message {
            display: none;
            padding: 15px;
            margin: 20px;
            border-radius: 5px;
            transition: opacity 0.3s;
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1000;
        }

        .message.success {
            background: #dff0d8;
            color: #3c763d;
        }

        .message.error {
            background: #f2dede;
            color: #a94442;
        }

        .book-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        .book-item {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            display: flex;
            max-width: 600px;
        }

        .book-item img {
            width: 150px;
            height: auto;
        }

        .book-details {
            padding: 15px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .book-title {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        .book-info {
            margin: 5px 0;
        }

        .add-to-cart {
            background-color: #28a745;
            border: none;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .add-to-cart:hover {
            background-color: #218838;
        }

        footer {
            background: #2c3e50;
            color: white;
            text-align: center;
            padding: 15px 0;
            position: relative;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>

<body>

    <!-- Header -->
    <div class="header">
        <div class="left">
            <div>Welcome, <%= session.getAttribute("userName") != null ? session.getAttribute("userName") : "Guest" %></div>
        </div>
        <div class="right">
            <button class="shop-button" onclick="getAllBooks()">Shop</button>
            <button onclick="toggleSearchBar()">Search <i class="fas fa-search"></i></button>
            <div id="searchBar" class="search-bar">
                <form action="searchServlet" method="get">
                    <input type="text" name="query" placeholder="Search books..." required>
                    <button type="submit">Go</button>
                </form>
            </div>
            <button onclick="location.href='browse.jsp'">Browse Products</button>
            <button onclick="location.href='getCartItems'">View Cart</button>
            <button onclick="location.href='logout'">Logout</button>
            <button class="order-history" onclick="location.href='orderHistory'">Order History</button>
        </div>
    </div>

    <!-- Cart success or error message -->
    <div class="message" id="message"></div>

    <!-- Dashboard Content -->
    <div class="book-container">
        <% 
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books == null || books.isEmpty()) { %>
            <p>No books available. Please check back later.</p>
        <% } else {
            for (Book book : books) { %>
            <div class="book-item">
                <img src="<%= book.getCoverImageUrl() %>" alt="<%= book.getTitle() %>">
                <div class="book-details">
                    <div class="book-title"><%= book.getTitle() %></div>
                    <div class="book-info"><strong>Author:</strong> <%= book.getAuthor() %></div>
                    <div class="book-info"><strong>Publisher:</strong> <%= book.getPublisher() %></div>
                    <div class="book-info"><strong>Genre:</strong> <%= book.getGenre() %></div>
                    <div class="book-info"><strong>Price:</strong> $<%= book.getPrice() %></div>
                    <button class="add-to-cart" onclick="addToCart(<%= book.getId() %>)">Add to Cart</button>
                </div>
            </div>
        <% } } %>
    </div>

    <footer>
        <p>&copy; 2024 YourCompany. All rights reserved.</p>
    </footer>

    <script>
        function showMessage(message, type) {
            const messageDiv = document.getElementById('message');
            messageDiv.textContent = message;
            messageDiv.className = `message ${type}`;
            messageDiv.style.display = 'block';
            setTimeout(() => {
                messageDiv.style.display = 'none';
            }, 3000);
        }

        function addToCart(bookId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "addToCartServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        showMessage("Book successfully added to cart!", "success");
                    } else {
                        showMessage("Failed to add book to cart. Please try again.", "error");
                    }
                }
            };
            xhr.send("bookId=" + encodeURIComponent(bookId));
        }

        function toggleSearchBar() {
            var searchBar = document.getElementById("searchBar");
            searchBar.style.display = searchBar.style.display === "block" ? "none" : "block";
        }

        function getAllBooks() {
            location.href = 'getAllBooksServlet';
        }
    </script>

</body>

</html>
