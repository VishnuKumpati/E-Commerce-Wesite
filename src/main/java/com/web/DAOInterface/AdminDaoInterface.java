package com.web.DAOInterface;

import java.util.List;

import com.web.Entity.Buyer;
import com.web.Entity.Retailer;

public interface AdminDaoInterface {

	List<Buyer> getAllBuyers();

	List<Retailer> getAllRetailers();

	Buyer getUserDetails(Long userId);

	boolean blockBuyer(Long buyerId);

	String getStatus(Long buyerId);
	String getStatusRetailer(Long buyerId);

	boolean blockRetailer(Long buyerId);

}
