<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
<div class="header">
    <div class="header-left" style="display: flex; align-items: center;">
        <!-- Inline CSS for logo text -->
        <div style="text-align: center; margin-right: 20px;">
            <h1 style="font-family: 'Arial', sans-serif; font-size: 24px; color: #2b3e42; margin: 0;">BOOKWORM</h1>
            <p style="font-family: 'Arial', sans-serif; font-size: 12px; color: #7b8e91; letter-spacing: 1px; margin: 0;">BREAKBOREDOM</p>
        </div>
    </div>
    <div class="header-right">
        <a href="#">Home</a>
        <a href="contactUs.jsp">Contact Us</a>
        <a href="register.jsp">Signup</a>
        <a href="login.jsp">Login</a>
    </div>
</div>

<!-- Full Page Section with Background Image -->
<div class="full-page" style="background-image: url('images/welcome.jpg'); 
background-size: cover; background-position: center; height: 100vh; display: flex; 
justify-content: center; align-items: center; color: white;">
    <h1>Welcome to Our Books Store</h1>
</div>


<!-- Static Images -->
<div class="static-images" style="background-color:#7C93C3">
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/onepiecevolume1.jpg" alt="One piece volume1">
    </div>
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/onepiecevolume3.jpg" alt="one piece volume 3">
    </div>
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/onepiecevolume2.jpg" alt="one piece volume 4">
    </div>
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/hunt.jpg" alt="Treasure hunt 4">
    </div>
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/kalki.jpg" alt="kalki">
    </div>
    <div class="static-image-box">
        <img onclick="redirectToLogin()" src="images/goat.jpg" alt="goat">
    </div>
</div>

<div class="carousel-container">
    <div class="carousel-images">
        <img onclick="redirectToLogin()" src="images/thetimemachine.jpg" alt="Time machine">
        <img onclick="redirectToLogin()" src="images/hishatbow.jpg" alt="Hat bow 8">
        <img onclick="redirectToLogin()" src="images/hamlet.jpg" alt="hamlet">
        <img onclick="redirectToLogin()" src="images/deathnotevolume2.jpg" alt="death note">
        <img onclick="redirectToLogin()" src="images/deathnote volume 1.jpg" alt="death note">
        <img onclick="redirectToLogin()" src="images/batman.jpg" alt="batman">
    </div>
</div>

<div class="separator"></div>

<div class="webpage-details">
    <h2>About This Website</h2>
    <p>Discover a wide range of electronics and gadgets at our online store. From the latest in technology to classic favorites, we offer products that meet your needs. For support or inquiries, please contact us.</p>
    <p><strong>Contact Us:</strong> support@mywebsite.com | +1 (555) 123-4567</p>
</div>

<script type="text/javascript">
function redirectToLogin() {
    window.location.href = "login.jsp";
}
</script>

</body>
</html>
