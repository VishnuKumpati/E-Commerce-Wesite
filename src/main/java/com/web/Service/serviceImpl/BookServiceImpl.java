package com.web.Service.serviceImpl;

import java.util.List;

import com.web.DAOImplementation.RegisterDAOImpl;
import com.web.DAOInterface.RegisterDaoInterface;
import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;
import com.web.ServiceInterface.BookServiceInterface;

public class BookServiceImpl implements BookServiceInterface{
	private RegisterDaoInterface dao;
	 public BookServiceImpl() {
		dao=GenericConfiguration.createInstance(RegisterDAOImpl.class);
	}

	 @Override
	    public List<Book> getBooksByRetailer(Long retailerId) {
	        return dao.getBooksByRetailer(retailerId);
	    }

	@Override
	public Book getBookById(Long bookId) {
		
		return dao.getBookById(bookId);
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean deleteBook(Long bookId) {
		
		return dao.deleteBook(bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		
		return dao.getAllBooks();
	}

	@Override
	public List<Book> searchBooks(String query) {
		return dao.searchBooks(query);
	}
}
