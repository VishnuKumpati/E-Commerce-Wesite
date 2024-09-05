<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Success</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('images/retailer.jpg'); 
            background-size: cover;
            background-position: center;
            color: #333;
        }
        header {
            background-color: #454141;
            color: #84c8e0;
            padding: 15px 0;
            text-align: center;
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        header h1 {
            margin: 0;
            font-size: 24px;
        }
        .container {
            width: 80%;
            margin: 100px auto 20px auto;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            text-align: center;
        }
        .message-box {
            background-color: rgba(173, 186, 177, 0.8);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }
        .message-box h2 {
            margin-bottom: 20px;
        }
        .message-box p {
            margin-bottom: 20px;
            font-size: 18px;
        }
        .message-box button {
            padding: 10px 15px;
            border: none;
            background-color: #454141;
            color: #fff;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        .message-box button:hover {
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
        <h1>Success</h1>
    </header>
    <div class="container">
        <div class="message-box">
            <h2>Success!</h2>
            <p><%= request.getAttribute("message") %></p>
            <form action="<%= request.getAttribute("nextPage") %>" method="post">
                <button type="submit">Go to <%= request.getAttribute("buttonLabel") %></button>
            </form>
        </div>
    </div>
    <footer>
        &copy; 2024 E-Commerce Project. All rights reserved.
    </footer>
</body>
</html>
