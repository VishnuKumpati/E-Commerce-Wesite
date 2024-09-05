package com.web.Service.serviceImpl;

import java.util.List;

import com.web.DAOImplementation.OrderDaoImpl;

import com.web.DAOInterface.OrderDaoInterface;

import com.web.Entity.Order;
import com.web.GenericConfig.GenericConfiguration;
import com.web.ServiceInterface.OrderServiceInterface;

public class OrderServiceImpl  implements OrderServiceInterface{
	private OrderDaoInterface dao;
    public OrderServiceImpl() {

		dao=GenericConfiguration.createInstance(OrderDaoImpl.class);
	}
	
	@Override
	public List<Order> getOrdersByRetailer(long retailerId) {
		
		return dao.getOrderByRetailer(retailerId);
	}

	@Override
	public List<Order> getOrderHistoryByUserId(Long userId) {
		// TODO Auto-generated method stub
		return dao.getOrderHistoryByUserId(userId);
	}

}
