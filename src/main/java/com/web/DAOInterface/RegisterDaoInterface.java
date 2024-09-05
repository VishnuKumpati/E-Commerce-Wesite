package com.web.DAOInterface;

import java.util.List;


import com.web.Entity.Book;
import com.web.Entity.Order;

public interface RegisterDaoInterface {

	String addUser(Object user, String userType);

	Object authenticateUser(String email, String password);

	String registerBook(Book book,Long retailerId);
	
	List<Book> getBooksByRetailer(Long retailerId);

	Book getBookById(Long bookId);

	boolean updateBook(Book book);

	boolean deleteBook(Long bookId);

	List<Book> getAllBooks();

	List<Book> searchBooks(String query);

	void addBookToCart(String userId, Book book);

	


   
}
