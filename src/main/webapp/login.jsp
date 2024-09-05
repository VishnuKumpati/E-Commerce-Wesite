<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title >Login Page</title>
<link rel="stylesheet" type="text/css" href="css/login.css"> <!-- Ensure this path is correct -->
<script>
    window.onload = function() {
        var errorMessage = "<%= request.getAttribute("errorMessage") %>";
        if (errorMessage && errorMessage.trim().length > 0) {
            alert(errorMessage);
        }
    };
</script>
</head>
<body style="background-image: url('images/logo1.png')">
<div class="login-container">
    <form action="LoginServlet" method="post">
        <h2 style="color: Black">Login</h2>
        <div class="input-group">
            <label for="email" style="color: black">Email</label>
            <input type="text" id="email" name="email" placeholder="Enter your registered email" required>
        </div>
        <div class="input-group">
            <label for="password" style="color: black">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="submit-btn">Submit</button>
        <div class="links">
            <a href="forgot-password.jsp">Forgot Password?</a>
            <a href="register.jsp">New User? Register</a>
        </div>
    </form>
</div>
</body>
</html>
