package com.web.ServiceInterface;

import java.util.List;

import com.web.Entity.Book;

public interface BookServiceInterface {

	List<Book> getBooksByRetailer(Long retailerId);

	Book getBookById(Long bookId);

	boolean updateBook(Book book);

	boolean deleteBook(Long bookId);

	List<Book> getAllBooks();

	List<Book> searchBooks(String query);

}
