<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <style>
        body {
            background-image: url('images/logo1.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            font-family: Arial, sans-serif;
        }

        .form-container {
            max-width: 400px; /* Adjust the width as needed */
            margin: 50px auto;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.8); /* Increased opacity for more visibility */
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Stronger shadow for better contrast */
            border: 1px solid rgba(0, 0, 0, 0.1); /* Optional: Light border to improve visibility */
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-group input[type="number"] {
            -moz-appearance: textfield;
        }

        .form-group input[type="number"]::-webkit-inner-spin-button,
        .form-group input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        .form-group .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }

        .form-group button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }

        #adminPassword {
            display: none;
        }

        .form-group.text-center {
            text-align: center;
        }

        .form-group.text-center a {
            color: #007bff;
            text-decoration: none;
            font-size: 16px;
        }

        .form-group.text-center a:hover {
            text-decoration: underline;
        }
    </style>
    <script type="text/javascript">
        function toggleAdminPassword() {
            var userType = document.getElementById("userType").value;
            var adminPasswordField = document.getElementById("adminPassword");
            adminPasswordField.style.display = (userType === "Admin") ? "block" : "none";
        }

        function validatePassword() {
            var password = document.getElementById("password").value;
            var errorMessage = "";

            if (!/(?=.*\d)/.test(password)) {
                errorMessage += "Password must contain at least one number.\n";
            }

            if (!/(?=.*[a-zA-Z])/.test(password)) {
                errorMessage += "Password must contain at least one letter.\n";
            }

            if (!/(?=.*[@#$%^&+=])/.test(password)) {
                errorMessage += "Password must contain at least one special character.\n";
            }

            if (password.length < 8) {
                errorMessage += "Password must be at least 8 characters long.\n";
            }

            document.getElementById("passwordError").innerText = errorMessage;
            return errorMessage === "";
        }

        function validateForm() {
            return validatePassword() && validateContactNo();
        }

        function validateContactNo() {
            var contactNo = document.getElementById("contactno").value;
            var contactNoError = "";

            if (contactNo.length !== 10 || isNaN(contactNo)) {
                contactNoError = "Contact number must be exactly 10 digits.";
            }

            document.getElementById("contactNoError").innerText = contactNoError;
            return contactNoError === "";
        }
    </script>
</head>

<body>
    <div class="form-container">
        <h2>Registration Form</h2>
        <form action="registerController" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
                <div id="passwordError" class="error"></div>
            </div>
            <div class="form-group">
                <input type="number" id="age" name="age" placeholder="Enter your age" required>
            </div>
            <div class="form-group">
                <input type="text" id="contactno" name="contactno" placeholder="Enter your contact number" required>
                <div id="contactNoError" class="error"></div>
            </div>
            <div class="form-group">
                <input type="text" id="city" name="city" placeholder="Enter your city" required>
            </div>
            <div class="form-group">
                <select id="userType" name="userType" onchange="toggleAdminPassword()" required>
                    <option value="">Select User Type</option>
                    <option value="Admin">Admin</option>
                    <option value="Buyer">Buyer</option>
                    <option value="Retailer">Retailer</option>
                </select>
            </div>
            <div class="form-group" id="adminPassword">
                <input type="password" id="adminPasswordField" name="adminPasswordField" placeholder="Enter admin password">
            </div>
            <div class="form-group text-center">
                <button type="submit">Register</button>
                <div id="link" class="mt-3">
                    <a href="login.jsp">Already Have an Account? Login</a>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
