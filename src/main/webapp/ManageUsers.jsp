<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.web.Entity.Buyer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users</title>
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
        .action-buttons {
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }
        button {
            background-color: #3498db;
            color: #ffffff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            display: inline-block; /* Add this line to make the buttons display inline */
            margin-top: 5px; 
        }
        .single-user-actions {
    display: flex;
    align-items: center; /* Center the buttons vertically */
    gap: 10px;
    margin-top: 10px;
}

.single-user-actions button {
    height: 30px; /* Define a fixed height for the buttons */
    padding: 0 15px; /* Add horizontal padding to the buttons */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
    display: flex;
    align-items: center;
}

.single-user-actions button:hover {
    filter: brightness(90%); /* Add a slight hover effect */
}

.single-user-actions .delete-button {
    background-color: #e74c3c;
    color: #ffffff;
}

.single-user-actions .delete-button:hover {
    background-color: #c0392b;
}

.single-user-actions .block-button {
    background-color: #f39c12;
    color: #ffffff;
}

.single-user-actions .block-button:hover {
    background-color: #e67e22;
}
        
        button:hover {
            background-color: #2980b9;
        }
        .delete-button {
            background-color: #e74c3c;
        }
        .delete-button:hover {
            background-color: #c0392b;
        }
        .block-button {
            background-color: #f39c12;
        }
        .block-button:hover {
            background-color: #e67e22;
        }
    </style>
    <script>
        
        function editUser(userId) {
            window.location.href = 'editUser?userId=' + userId;
        }

        function deleteUser(userId) {
            if (confirm('Are you sure you want to delete User ID ' + userId + '?')) {
                window.location.href = 'deleteUser?userId=' + userId;
            }
        }

        function blockUser(userId) {
            if (confirm('Are you sure you want to block User ID ' + userId + '?')) {
                window.location.href = 'block?userId=' + userId;
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Manage Users</h2>

        <% 
            // Check for single buyer details
            Buyer buyer = (Buyer) request.getAttribute("user");
            if (buyer != null) {
        %>
        <div class="buyer-card">
            <div class="buyer">
                <div class="buyer-info"><label>ID:</label> <%= buyer.getId() %></div>
                <div class="buyer-info"><label>Name:</label> <%= buyer.getName() %></div>
                <div class="buyer-info"><label>Email:</label> <%= buyer.getEmail() %></div>
                <div class="buyer-info"><label>Age:</label> <%= buyer.getAge() %></div>
                <div class="buyer-info"><label>Contact Number:</label> <%= buyer.getContactno() %></div>
                <div class="buyer-info"><label>City:</label> <%= buyer.getCity() %></div>
                <div class="buyer-info"><label>User Type:</label> <%= buyer.getUserType() %></div>
                
            </div>
               <div class="action-buttons single-user-actions">
               
                <button class="delete-button" onclick="deleteUser('<%= buyer.getId() %>')">Delete</button>
                <button class="block" onclick="blockUser('<%= buyer.getId() %>')">Block</button>
    </div>
        </div>
        <% 
            } else {
                // Check for list of buyers
                List<Buyer> buyers = (List<Buyer>) request.getAttribute("buyers");
                if (buyers != null && !buyers.isEmpty()) {
        %>
        <div class="buyer-card">
            <% 
                for (Buyer user : buyers) {
            %>
            <div class="buyer">
                <div class="buyer-info"><label>ID:</label> <%= user.getId() %></div>
                <div class="buyer-info"><label>Name:</label> <%= user.getName() %></div>
                <div class="buyer-info"><label>Email:</label> <%= user.getEmail() %></div>
                <div class="buyer-info"><label>Age:</label> <%= user.getAge() %></div>
                <div class="buyer-info"><label>Contact Number:</label> <%= user.getContactno() %></div>
                <div class="buyer-info"><label>City:</label> <%= user.getCity() %></div>
                <div class="buyer-info"><label>User Type:</label> <%= user.getUserType() %></div>

                <div class="action-buttons">
                  
                    <button class="delete-button" onclick="deleteUser('<%= user.getId() %>')">Delete</button>
                    <button class="block-button" onclick="blockUser('<%= user.getId() %>')">Block</button>
                </div>
            </div>
            <% 
                }
            %>
        </div>
        <% 
            }
        }
        %>
    </div>
</body>
</html>
