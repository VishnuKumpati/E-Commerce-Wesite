package com.web.DAOInterface;

import java.util.List;

import com.web.Entity.Order;

public interface OrderDaoInterface {



	List<Order> getOrderByRetailer(long retailerId);

	List<Order> getOrderHistoryByUserId(Long userId);

}
