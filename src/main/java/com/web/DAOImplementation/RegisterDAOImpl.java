package com.web.DAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.web.DAOInterface.RegisterDaoInterface;
import com.web.Entity.Admin;
import com.web.Entity.Book;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.GenericConfig.DBconfiguration;
import com.web.Service.serviceImpl.BookServiceImpl;

public class RegisterDAOImpl implements RegisterDaoInterface {
    private Connection con;

    public RegisterDAOImpl() {
        DBconfiguration dbConfig = new DBconfiguration();
        this.con = dbConfig.getConnection();
    }

    @Override
    public String addUser(Object user, String userType) {
        String query = "";
        PreparedStatement pst = null;
        long userId = -1;

        try {
            switch (userType) {
                case "Admin":
                    query = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";
                    pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    Admin admin = (Admin) user;
                    pst.setString(1, admin.getName());
                    pst.setString(2, admin.getEmail());
                    pst.setString(3, admin.getPassword());
                    break;

                case "Buyer":
                    query = "INSERT INTO buyer (name, email, password, age, contactno, city, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    Buyer buyer = (Buyer) user;
                    pst.setString(1, buyer.getName());
                    pst.setString(2, buyer.getEmail());
                    pst.setString(3, buyer.getPassword());
                    pst.setInt(4, buyer.getAge());
                    pst.setString(5, String.valueOf(buyer.getContactno())); // Ensure contact number is a String
                    pst.setString(6, buyer.getCity());
                    pst.setString(7, buyer.getUserType());
                    break;

                case "Retailer":
                   query = "INSERT INTO retailer (name, email, contactNumber, city, password) VALUES (?, ?, ?, ?, ?)";
                	pst = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                	Retailer retailer = (Retailer) user;
                	pst.setString(1, retailer.getName());
                	pst.setString(2, retailer.getEmail());
                	pst.setString(3, retailer.getContactNumber());
                	pst.setString(4, retailer.getCity());  // Ensure city is the correct column
                	pst.setString(5, retailer.getPassword());

                    break;

                default:
                    return "Invalid user type.";
            }

            int result = pst.executeUpdate();

            if (result > 0) {
                // Retrieve generated ID
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getLong(1);
                    // Set user ID to the object
                    if (user instanceof Admin) {
                        ((Admin) user).setId(userId);
                    } else if (user instanceof Buyer) {
                        ((Buyer) user).setId(userId);
                    } else if (user instanceof Retailer) {
                        ((Retailer) user).setId(userId);
                    }
                }
                return userType + " registered successfully with ID: " + userId;
            } else {
                return "Failed to register " + userType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error registering " + userType + ": " + e.getMessage();
        } 
    }
    @Override
    public Object authenticateUser(String email, String password) {
        String query;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            // Check in the Admin table
            query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }

            // Check in the Buyer table
            query = "SELECT * FROM buyer WHERE email = ? AND password = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                if ("blocked".equalsIgnoreCase(status)) {
                    return "blocked";
                }
                Buyer buyer = new Buyer();
                buyer.setId(rs.getLong("id"));
                buyer.setName(rs.getString("name"));
                buyer.setEmail(rs.getString("email"));
                return buyer;
            }

            // Check in the Retailer table
            query = "SELECT * FROM retailer WHERE email = ? AND password = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                if ("blocked".equalsIgnoreCase(status)) {
                    return "blocked";
                }
                Retailer retailer = new Retailer();
                retailer.setId(rs.getLong("id"));
                retailer.setName(rs.getString("name"));
                retailer.setEmail(rs.getString("email"));
                retailer.setContactNumber(rs.getString("contactNumber"));
                return retailer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if user not found in any table
    }

    @Override
    public String registerBook(Book book, Long retailerId) {
        String checkQuery = "SELECT COUNT(*) FROM book WHERE isbn = ? AND title = ?";
        String insertQuery = "INSERT INTO book (isbn, title, author, publisher, publicationDate, genre, language, price, stockQuantity, description, coverImageUrl, retailerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Check if the book already exists
            try (PreparedStatement checkPst = con.prepareStatement(checkQuery)) {
                checkPst.setString(1, book.getIsbn());
                checkPst.setString(2, book.getTitle());
                ResultSet rs = checkPst.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    return "A book with the same ISBN and title already exists.";
                }
            }

            // Insert the new book with the retailerId
            try (PreparedStatement insertPst = con.prepareStatement(insertQuery)) {
                insertPst.setString(1, book.getIsbn());
                insertPst.setString(2, book.getTitle());
                insertPst.setString(3, book.getAuthor());
                insertPst.setString(4, book.getPublisher());

                if (book.getPublicationDate() != null) {
                    insertPst.setDate(5, java.sql.Date.valueOf(book.getPublicationDate()));
                } else {
                    insertPst.setNull(5, java.sql.Types.DATE);
                }

                insertPst.setString(6, book.getGenre());
                insertPst.setString(7, book.getLanguage());
                insertPst.setDouble(8, book.getPrice());
                insertPst.setInt(9, book.getStockQuantity());
                insertPst.setString(10, book.getDescription());
                insertPst.setString(11, book.getCoverImageUrl());
                insertPst.setLong(12, retailerId);

                int result = insertPst.executeUpdate();
                if (result > 0) {
                    return book.getTitle();
                } else {
                    return "Failed to register book.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error registering book: " + e.getMessage();
        }
    }


    @Override
    public List<Book> getBooksByRetailer(Long retailerId) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE retailerId = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setLong(1, retailerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                
                java.sql.Date publicationDate = resultSet.getDate("publicationDate");
                if (publicationDate != null) {
                    book.setPublicationDate(publicationDate.toLocalDate());
                } 

                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setPrice(resultSet.getDouble("price"));
                book.setStockQuantity(resultSet.getInt("stockQuantity"));
                book.setDescription(resultSet.getString("description"));
                book.setCoverImageUrl(resultSet.getString("coverImageUrl"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Book getBookById(Long bookId) {
        Book book = null;
        String query = "SELECT * FROM book WHERE id = ?";

 
             PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(query);
				 stmt.setLong(1, bookId);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                book = new Book();
		                book.setId(rs.getLong("id"));
		                book.setTitle(rs.getString("title"));
		                book.setAuthor(rs.getString("author"));
		                book.setPrice(rs.getDouble("price"));
		                book.setStockQuantity(rs.getInt("stockQuantity"));
		                book.setCoverImageUrl(rs.getString("coverImageUrl"));
		                // Set other book properties as needed
		            }

			} 
           
          catch (SQLException e) {
				e.printStackTrace();
			} 
         

        return book;
    }

    @Override
    public boolean updateBook(Book book) {
        boolean isUpdated = false;
        String query = "UPDATE book SET title = ?, author = ?, price = ?, stockQuantity = ?, coverImageUrl = ? WHERE id = ?";

        try {
             PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDouble(3, book.getPrice());
            stmt.setInt(4, book.getStockQuantity());
            stmt.setString(5, book.getCoverImageUrl());
            stmt.setLong(6, book.getId());

            int rowsAffected = stmt.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    @Override
    public boolean deleteBook(Long bookId) {
        // Check if the book is associated with any orders
        String checkOrdersQuery = "SELECT COUNT(*) FROM orders WHERE book_id = ?";
        System.out.println(bookId+":"+"recevied in dao");
        try (PreparedStatement checkStmt = con.prepareStatement(checkOrdersQuery)) {
            checkStmt.setLong(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cannot delete book with existing orders.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Proceed to delete the book if no orders are associated
        String deleteBookQuery = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement deleteStmt = con.prepareStatement(deleteBookQuery)) {
            deleteStmt.setLong(1, bookId);
            int rowsAffected = deleteStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

      @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn")); // Add if you have this column
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));

                // Handle possible null for publicationDate
                java.sql.Date publicationDate = resultSet.getDate("publicationDate");
                if (publicationDate != null) {
                    book.setPublicationDate(publicationDate.toLocalDate());
                } 

                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setPrice(resultSet.getDouble("price"));
                book.setStockQuantity(resultSet.getInt("stockQuantity"));
                book.setDescription(resultSet.getString("description"));
                book.setCoverImageUrl(resultSet.getString("coverImageUrl"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }

        return books;
    }

    @Override
    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title LIKE ? OR author LIKE ?"; // Adjust the SQL query as needed

        try {
             PreparedStatement statement = con.prepareStatement(sql);
            String searchQuery = "%" + query + "%";
            statement.setString(1, searchQuery);
            statement.setString(2, searchQuery);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getLong("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setDescription(resultSet.getString("description"));
                    book.setCoverImageUrl(resultSet.getString("coverImageUrl"));
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public void addBookToCart(String userId, Book book) {
        String query = "INSERT INTO cart (userId, bookId, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setLong(2, book.getId());
            preparedStatement.setInt(3, 1); // Default quantity is set to 1 for now

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	

   

}



    
