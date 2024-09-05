<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.web.Entity.Retailer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Retailers</title>
    <link rel="stylesheet" href="css/retailer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <style>
       
    </style>
</head>
<body>
    <div class="container">
        <h2>Manage Retailers</h2>

        <%
            List<Retailer> retailers = (List<Retailer>) request.getAttribute("retailers");
            if (retailers != null) {
                out.println("<p ><b>Number of retailers: " + retailers.size() + " <b></p>");
                for (Retailer retailer : retailers) {
        %>
        <div class="user-card">
            <div class="user-info">ID: <%= retailer.getId() %></div>
            <div class="user-info">Name: <%= retailer.getName() %></div>
            <div class="user-info">Email: <%= retailer.getEmail() %></div>
            <div class="user-info">City: <%= retailer.getCity() %></div>
            <div class="user-info">Contact Number: <%= retailer.getContactNumber() %></div>
            <div class="button-group">
                
                <a href="deleteRetailer?id=<%= retailer.getId() %>"><button class="delete">Delete</button></a>
              <a href="blockRetailer?id=<%= retailer.getId() %>"><button class="block">Block</button></a>

            </div>
        </div>
        <%
                }
            }
        %>    
    </div>
</body>
</html>
