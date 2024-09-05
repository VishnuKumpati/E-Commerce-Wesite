package com.web.GenericConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfiguration {
    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String userName = "root";
    private static final String password = "vishnu";
    
  Connection connection;
    
    // Constructor to initialize the connection
    public DBconfiguration() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish a database connection", e);
        }
    }
    
    // Method to return the existing connection
    public Connection getConnection() {
        return connection;
    }
}
