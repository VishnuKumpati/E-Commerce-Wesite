<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Dashboard</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        header {
            background-color: #454141;
            color: #84c8e0;
            padding: 15px 0;
            text-align: center;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 20px;
        }
        header h1 {
            margin: 0;
            font-size: 24px;
        }
        .welcome-message {
            color: #84c8e0;
        }
        .logout-button {
            background-color: #ff4d4d;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
        }
        .logout-button:hover {
            background-color: #e60000;
        }
        .container {
            width: 80%;
            margin: 80px auto 20px auto;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        .card {
            background-color: #e3e3e3;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 100%;
            max-width: 300px;
            text-align: center;
            color: #333;
        }
        .card h3 {
            margin-top: 0;
            font-size: 20px;
        }
        .card p {
            margin: 10px 0;
            color: #666;
        }
        .card a {
            text-decoration: none;
            color: #fff;
            background-color: #454141;
            padding: 10px 15px;
            border-radius: 5px;
            display: inline-block;
            margin-top: 15px;
            font-weight: bold;
        }
        .card a:hover {
            background-color: #0056b3;
        }
        footer {
            background-color: #454141;
            color: #84c8e0;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Retailer Dashboard</h1>
        <div class="welcome-message">
            Welcome, <%= session.getAttribute("userName") %>
        </div>
        <a href="logout" class="logout-button">Logout</a>
    </header>

    <div class="container">
        <div class="card">
            <h3>Register as Buyer</h3>
            <p>Register new buyers for your products.</p>
            <a href="register.jsp">Register</a>
        </div>
        <div class="card">
            <h3>Add Product</h3>
            <p>Add new products to your inventory.</p>
            <a href="bookRegister.jsp">Add Product</a>
        </div>
        <div class="card">
            <h3>View Orders</h3>
            <p>Check and manage incoming orders.</p>
            <a href="viewOrders">View Orders</a>
        </div>
        <div class="card">
            <h3>Manage Inventory</h3>
            <p>Update and keep track of your product inventory.</p>
            <a href="ManageInventoryServlet">Manage Inventory</a>
        </div>
    </div>
    <footer>
        &copy; 2024 E-Commerce Project. All rights reserved.
    </footer>
</body>
</html>
