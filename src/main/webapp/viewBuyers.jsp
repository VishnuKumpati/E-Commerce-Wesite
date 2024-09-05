<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.web.Entity.Buyer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Industrial Level Buyer Review</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e0e0e0;
            color: #333;
        }
        .container {
            max-width: 900px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h2 {
            margin-top: 0;
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        .buyer-card {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }
        .buyer {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
        }
        .buyer-info {
            margin-bottom: 10px;
            font-size: 16px;
        }
        .buyer-info label {
            font-weight: bold;
            color: #2c3e50;
        }
        button {
            background-color: #3498db;
            color: #ffffff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
     <script>
     function takeActionAndRedirect(userId) {
    	    if (confirm('Additional Actions for User ID ' + userId)) {
    	        window.location.href = 'userDetails?userId=' + userId; // Pass userId as parameter
    	    }
    	}

</script>

</head>
<body>
    <div class="container">
        <h2> Buyer Review </h2>

        <div class="buyer-card">
            <% 
                List<Buyer> buyers = (List<Buyer>) request.getAttribute("buyers");
                if (buyers != null) {
                    for (Buyer buyer : buyers) {
            %>
            <div class="buyer">
                <div class="buyer-info"><label>ID:</label> <%= buyer.getId() %></div>
                <div class="buyer-info"><label>Name:</label> <%= buyer.getName() %></div>
                <div class="buyer-info"><label>Email:</label> <%= buyer.getEmail() %></div>
                <div class="buyer-info"><label>Age:</label> <%= buyer.getAge() %></div>
                <div class="buyer-info"><label>Contact Number:</label> <%= buyer.getContactno() %></div>
                <div class="buyer-info"><label>City:</label> <%= buyer.getCity() %></div>
                <div class="buyer-info"><label>User Type:</label> <%= buyer.getUserType() %></div>
                <button onclick="takeActionAndRedirect('<%= buyer.getId() %>')">Take Action</button>

            </div>
            <% 
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
