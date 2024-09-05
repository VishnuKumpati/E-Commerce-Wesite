package com.web.Service.serviceImpl;

import java.util.List;

import com.web.DAOImplementation.AdminDaoImpl;
import com.web.DAOInterface.AdminDaoInterface;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.GenericConfig.GenericConfiguration;
import com.web.ServiceInterface.AdminServiceInterface;

public class AdminServiceImpl implements AdminServiceInterface{
	private AdminDaoInterface dao;
	

	public AdminServiceImpl() {
		this.dao=GenericConfiguration.createInstance(AdminDaoImpl.class);
		
	}

	@Override
	public List<Buyer> getAllBuyers() {
		
		return dao.getAllBuyers();
	}

	@Override
	public List<Retailer> getAllRetailers() {
		// TODO Auto-generated method stub
		return dao.getAllRetailers();
	}

	@Override
	public Buyer getUserDetails(Long userId) {
		// TODO Auto-generated method stub
		return dao.getUserDetails(userId);
	}

	@Override
	public boolean blockBuyer(Long buyerId) {
		
		return dao.blockBuyer(buyerId);
	}

	@Override
	public String getStatus(Long buyerId) {
		// TODO Auto-generated method stub
		return dao.getStatus(buyerId);
	}

	@Override
	public boolean blockRetailer(Long buyerId) {
		// TODO Auto-generated method stub
		return dao.blockRetailer(buyerId);
	}

	@Override
	public String getStatusRetailer(Long buyerId) {
		// TODO Auto-generated method stub
		return dao.getStatusRetailer(buyerId);
	}
	

}
