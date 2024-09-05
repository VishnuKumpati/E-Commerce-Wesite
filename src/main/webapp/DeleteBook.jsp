<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Book</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Delete Book</h2>
    <p>Are you sure you want to delete the book titled "<%= request.getAttribute("title") %>"?</p>
    <form action="DeleteBookServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getAttribute("bookId") %>">
        <button type="submit">Yes, Delete</button>
        <a href="manageInventory.jsp">Cancel</a>
    </form>
</body>
</html>
