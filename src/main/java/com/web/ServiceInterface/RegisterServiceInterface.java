package com.web.ServiceInterface;

import com.web.Entity.Book;

public interface RegisterServiceInterface {

	String addUser(Object user, String userType);

	Object authenticateUser(String email, String password);

	String registerBook(Book book,Long registryId);

	

	

	

}
