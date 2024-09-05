<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us</title>
   <link rel="stylesheet" href="css/contactUs.css">
</head>
<body>
    <div class="header">
        <a href="login.jsp">Back to Login</a>
    </div>
    <div class="container">
        <h2>Contact Us</h2>
        <div class="info">
            <p><strong>We are here to help!</strong></p>
            <p>Please send your complaints or inquiries to: <strong>bookworm@commercial.gmail.com</strong></p>
        </div>
        <form action="login.jsp" method="post">
            <label for="name">Your Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter your name" required>

            <label for="email">Your Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" placeholder="Enter your message here..." required></textarea>

            <input type="submit" value="Send Message">
        </form>
    </div>
</body>
</html>
