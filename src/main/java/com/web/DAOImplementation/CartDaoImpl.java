package com.web.DAOImplementation;

import com.web.DAOInterface.CartDao;
import com.web.Entity.Book;
import com.web.GenericConfig.DBconfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private Connection con;

    public CartDaoImpl() {
        DBconfiguration dbConfig = new DBconfiguration();
        this.con = dbConfig.getConnection();
    }

  
    @Override
    public List<Book> getCartItems(Long userId) {
        List<Book> cartItems = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM cart INNER JOIN book ON cart.bookid = book.id WHERE cart.userid = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("bookId"));
                System.out.println(book.getId());
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                book.setCoverImageUrl(resultSet.getString("coverimageurl"));
                // Set other fields as necessary
                cartItems.add(book);
                System.out.println("Dao  "+book.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error
            throw new RuntimeException("Error retrieving cart items", e);
        } finally {
            // Close resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log closing error
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Log closing error
                }
            }
        }
        return cartItems;
    }


    @Override
    public boolean addBookToCart(long userId, long bookId) {
        String sql = "INSERT INTO cart (userId, bookId, quantity) VALUES (?, ?, 1) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + 1";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, bookId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean removeBookFromCart(Long userId, long bookId) {
        System.out.println("Attempting to delete user ID: " + userId + ", book ID: " + bookId);
        
        String sql = "DELETE FROM cart WHERE bookId = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            //preparedStatement.setLong(1, userId);
            preparedStatement.setLong(1, bookId);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            return rowsAffected > 0; // No need to commit if using auto-commit
            
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    }



