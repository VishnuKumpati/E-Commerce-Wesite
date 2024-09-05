<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body>

<!-- Header Section -->
<header class="header">
    <div class="container" style="display: flex; justify-content: space-between; align-items: center;">
        <h1>Admin Dashboard</h1>
        <div style="display: flex; align-items: center;">
            <div class="user-welcome" style="margin-right: 20px;">Welcome, <strong>Sunny</strong></div>
            <button onclick="window.location.href='login.jsp';" 
                style="padding: 10px 20px; background-color: #ff4c4c; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 14px;">
                Logout
            </button>
        </div>
    </div>
</header>

<!-- Main Content Section -->
<main class="main-content">
    <div class="container">
        <section class="tasks">
            <h2>What You Can Do:</h2>
            <div class="task-grid">
                <div class="task">
                    <i class="fas fa-users"></i>
                    <h3><a href="viewBuyers?viewAction=viewUsers">View All Users</a></h3>
                    <p>Examine user profiles, activities, and statistics.</p>
                </div>
                <div class="task">
                    <i class="fas fa-user-cog"></i>
                    <h3><a href="viewBuyers?manageAction=manageUsers">Manage Users</a></h3>
                    <p>Edit, delete, or promote user accounts.</p>
                </div>
                <div class="task">
                    <i class="fas fa-store"></i>
                    <h3><a href="viewRetailers?manageAction=viewRetailers">View All Retailers</a></h3>
                    <p>Browse and analyze information about retailers.</p>
                </div>
                <div class="task">
                    <i class="fas fa-cogs"></i>
                    <h3><a href="viewRetailers?manageAction=manageRetailers">Manage Retailers</a></h3>
                    <p>Update retailer details, statuses, and settings.</p>
                </div>
                <div class="task">
                    <i class="fas fa-comment-dots"></i>
                    <h3><a href="view_complaints.html">View Complaints</a></h3>
                    <p>Respond to and manage user complaints effectively.</p>
                </div>
                <div class="task">
                    <i class="fas fa-user-plus"></i>
                    <h3><a href="registration_requests.html">View Registration Requests</a></h3>
                    <p>Review, approve, or deny new user registrations.</p>
                </div>
                <!-- Add more tasks here if needed -->
            </div>
        </section>
    </div>
</main>

<!-- Footer Section -->
<footer class="footer">
    <div class="container">
        <p>&copy; 2024 YourCompany. All rights reserved.</p>
        <p>
            <a href="privacy_policy.html">Privacy Policy</a> |
            <a href="terms_of_service.html">Terms of Service</a>
        </p>
    </
