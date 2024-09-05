package com.web.ServiceInterface;

import java.util.List;

import com.web.Entity.Book;

public interface CartServiceInterface {

	 boolean addBookToCart(long userId, long bookId); 
	 List<Book> getCartItems(Long buyerId);
	boolean removeBookFromCart(Long userId, long bookId);

	



}
