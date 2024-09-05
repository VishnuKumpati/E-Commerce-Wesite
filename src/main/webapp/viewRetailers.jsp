<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.web.Entity.Retailer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Industrial Level Retailer Review</title>
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
        .retailer-card {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }
        .retailer {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
            display: grid;
            grid-template-columns: 1fr 1fr;
        }
        .retailer-info {
            margin-bottom: 10px;
            font-size: 16px;
        }
        .retailer-info label {
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
        function takeActionAndRedirect(name) {
            if (confirm('Additional Actions for Retailer ' + name)) {
                window.location.href = "manageRetailers.jsp";
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2> Retailer Review </h2>

        <div class="retailer-card">
            <% 
                List<Retailer> retailers = (List<Retailer>) request.getAttribute("retailers");
                if (retailers != null) {
                    for (Retailer retailer : retailers) {
            %>
            <div class="retailer">
                <div class="retailer-info"><label>ID:</label> <%= retailer.getId() %></div>
                <div class="retailer-info"><label>Name:</label> <%= retailer.getName() %></div>
                <div class="retailer-info"><label>Email:</label> <%= retailer.getEmail() %></div>
                <div class="retailer-info"><label>Contact Number:</label> <%= retailer.getContactNumber() %></div>
                <div class="retailer-info"><label>City:</label> <%= retailer.getCity() %></div>
                <!-- Space for Retailer Photo -->
                <div>Insert Retailer Photo Here</div>
                <button onclick="takeActionAndRedirect('<%= retailer.getName() %>')">Take Action</button>
            </div>
            <% 
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
