package com.web.DAOInterface;

import java.util.List;

import com.web.Entity.Book;

public interface CartDao {

	boolean addBookToCart(long userId, long bookId);

	List<Book> getCartItems(Long userId);

	boolean removeBookFromCart(Long userId, long bookId);

}
