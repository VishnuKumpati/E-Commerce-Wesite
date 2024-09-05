package com.web.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.DAOInterface.AdminDaoInterface;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.GenericConfig.DBconfiguration;

public class AdminDaoImpl implements AdminDaoInterface {

    private Connection con;

    public AdminDaoImpl() {
        DBconfiguration dBconfig = new DBconfiguration();
        this.con = dBconfig.getConnection();
    }

    @Override
    public List<Buyer> getAllBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        try {
            String qry = "SELECT * FROM buyer";
            PreparedStatement preparedStatement = con.prepareStatement(qry);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(resultSet.getLong("id"));
                buyer.setName(resultSet.getString("name"));
                buyer.setEmail(resultSet.getString("email"));
                buyer.setAge(resultSet.getInt("age"));
                buyer.setContactno(resultSet.getLong("contactno"));
                buyer.setCity(resultSet.getString("city"));
                buyer.setUserType(resultSet.getString("userType"));
                
                buyers.add(buyer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyers;
    }

    @Override
    public List<Retailer> getAllRetailers() {
        List<Retailer> retailers = new ArrayList<>();
        try {
            String qry = "SELECT * FROM retailer";
            PreparedStatement preparedStatement = con.prepareStatement(qry);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Retailer retailer = new Retailer();
                retailer.setId(resultSet.getLong("id"));
                retailer.setName(resultSet.getString("name"));
                retailer.setEmail(resultSet.getString("email"));
                retailer.setContactNumber(resultSet.getString("contactNumber"));
                retailer.setCity(resultSet.getString("city"));
                retailers.add(retailer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailers;
    }

    @Override
    public Buyer getUserDetails(Long userId) {
        try {
            String query = "SELECT id, name, email, age, contactno, city, userType FROM buyer WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(resultSet.getLong("id"));
                buyer.setName(resultSet.getString("name"));
                buyer.setEmail(resultSet.getString("email"));
                buyer.setAge(resultSet.getInt("age"));
                buyer.setContactno(resultSet.getLong("contactno"));
                buyer.setCity(resultSet.getString("city"));
                buyer.setUserType(resultSet.getString("userType"));
                System.out.println("hello hi");
                System.out.println("from dao: " + buyer);
                return buyer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean blockBuyer(Long buyerId) {
    	boolean operationSuccess = false;

        try {
    
            String updateQuery = "UPDATE buyer SET status = 'blocked' WHERE id = ?";
            PreparedStatement p = con.prepareStatement(updateQuery);
            p.setLong(1, buyerId);

            int rowsAffected = p.executeUpdate(); // Execute the update query

            if (rowsAffected > 0) {
                // The update operation was successful
                operationSuccess = true;
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace(); // Log or handle the exception as per your application's error handling strategy
        } 

        return operationSuccess;
    }

    @Override
    public String getStatus(Long buyerId) {
        String status = null;

        try {
            String query = "SELECT status FROM buyer WHERE id = ?";
            PreparedStatement p = con.prepareStatement(query);
            p.setLong(1, buyerId);

            ResultSet r = p.executeQuery(); // Execute the query

            if (r.next()) {
                // Get the status from the result set
                status = r.getString("status");
                System.out.println(status); // Print to console for debugging purposes
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as per your application's error handling strategy
        }

        return status; // Return the retrieved status
    }
    @Override
    public String getStatusRetailer(Long buyerId) {
        String status = null;

        try {
            String query = "SELECT status FROM retailer WHERE id = ?";
            PreparedStatement p = con.prepareStatement(query);
            p.setLong(1, buyerId);

            ResultSet r = p.executeQuery(); // Execute the query

            if (r.next()) {
                // Get the status from the result set
                status = r.getString("status");
                System.out.println(status); // Print to console for debugging purposes
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as per your application's error handling strategy
        }

        return status; // Return the retrieved status
    }

    @Override
    public boolean blockRetailer(Long buyerId) {
       
        boolean success = false;

        try {
            
            String query = "UPDATE retailer SET status = 'blocked' WHERE id = ?";
            PreparedStatement p = con.prepareStatement(query);
             p.setLong(1, buyerId);

            // Execute the update query
            int rowsUpdated = p.executeUpdate();

            if (rowsUpdated > 0) {
                success = true; // Updating status to "blocked" was successful
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace(); // Log or handle the exception as per your application's error handling strategy
        } 

        return success; // Return whether the retailer blocking was successful
    }

}
