<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Placed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .header {
            background-color: #2c3e50;
            color: #fff;
            padding: 10px 20px;
            width: 100%;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            position: relative;
        }
        .header-buttons {
            position: absolute;
            right: 20px;
            top: 50%;
            transform: translateY(-50%);
        }
        .header-buttons a {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
        }
        .header-buttons a:hover {
            background-color: #2980b9;
        }
        .message-container {
            text-align: center;
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .message-container h1 {
            font-size: 36px;
            margin-bottom: 20px;
            color: #27ae60;
        }
        .message-container p {
            font-size: 18px;
            color: #333;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <div class="header">
        <h1>Order Placed</h1>
        <div class="header-buttons">
            <!-- Back to Dashboard Button -->
            <a href="buyerDashboard.jsp">Back to Dashboard</a>
        </div>
    </div>

    <!-- Order Placed Message -->
    <div class="message-container">
        <h1>Thank You!</h1>
        <p>Your order has been successfully placed.</p>
    </div>

</body>
</html>
