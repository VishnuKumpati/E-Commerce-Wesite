package com.web.DAOImplementation;

import com.web.DAOInterface.OrderDaoInterface;
import com.web.Entity.Order;
import com.web.GenericConfig.DBconfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDaoInterface {
	
private Connection con;
    public OrderDaoImpl() {
		DBconfiguration dbConfig=new DBconfiguration();
		con=dbConfig.getConnection();
	}

    @Override
    public List<Order> getOrderByRetailer(long retailerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.id, o.buyer_id, o.book_id, o.retailer_id, o.quantity, o.total_price, o.order_date, b.title, b.author " +
                     "FROM orders o " +
                     "JOIN book b ON o.book_id = b.id " +
                     "WHERE o.retailer_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, retailerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setBuyerId(resultSet.getLong("buyer_id"));
                order.setBookId(resultSet.getLong("book_id"));
                order.setRetailerId(resultSet.getLong("retailer_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setTitle(resultSet.getString("title")); // Set the book title
                order.setAuthor(resultSet.getString("author")); // Set the book author
                
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

	@Override
	public List<Order> getOrderHistoryByUserId(Long userId) {
	    List<Order> orderHistory = new ArrayList<>();
	    String query = "SELECT o.id, o.buyer_id, o.book_id, o.retailer_id, o.quantity, o.total_price, o.order_date, b.title, b.author " +
	                   "FROM orders o " +
	                   "JOIN book b ON o.book_id = b.id " +
	                   "WHERE o.buyer_id = ?";

	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setLong(1, userId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getLong("id"));
	            order.setBuyerId(rs.getLong("buyer_id"));
	            order.setBookId(rs.getLong("book_id"));
	            order.setRetailerId(rs.getLong("retailer_id"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setTotalPrice(rs.getDouble("total_price"));
	            order.setOrderDate(rs.getTimestamp("order_date"));
	            order.setTitle(rs.getString("title"));
	            order.setAuthor(rs.getString("author"));
	            order.setStatus("Pending"); // Set default status to "Pending"

	            orderHistory.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception
	    }
	    return orderHistory;
	}

}