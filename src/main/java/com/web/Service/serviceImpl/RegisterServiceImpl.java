package com.web.Service.serviceImpl;

import com.web.DAOImplementation.RegisterDAOImpl;
import com.web.DAOInterface.RegisterDaoInterface;
import com.web.Entity.Book;
import com.web.Entity.Buyer;
import com.web.GenericConfig.GenericConfiguration;
import com.web.ServiceInterface.RegisterServiceInterface;

public class RegisterServiceImpl implements RegisterServiceInterface{
	private RegisterDaoInterface dao;
	public RegisterServiceImpl() {
		dao=GenericConfiguration.createInstance(RegisterDAOImpl.class);
	}
	@Override
	public String addUser(Object user, String userType) {
		
		return dao.addUser(user,userType);
	}
	@Override
	public Object authenticateUser(String email, String password) {
		
		return dao.authenticateUser(email,password);
	}
	@Override
	public String registerBook(Book book,Long retailerId) {
		
		return dao.registerBook(book,retailerId);
	}

	

	
}
