package com.web.ServiceInterface;

import java.util.List;

import com.web.Entity.Order;


public interface OrderServiceInterface {

	List<Order> getOrdersByRetailer(long retailerId);

	List<Order> getOrderHistoryByUserId(Long userId);

}
