
package com.web.Service.serviceImpl;
import java.util.List;

import com.web.DAOImplementation.CartDaoImpl;
import com.web.DAOInterface.CartDao;
import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;
import com.web.ServiceInterface.CartServiceInterface;

public class CartServiceImpl implements CartServiceInterface {
    private CartDao dao;

    public CartServiceImpl() {
        dao = GenericConfiguration.createInstance(CartDaoImpl.class);
    }

	

	@Override
	public List<Book> getCartItems(Long userId) {
		
		return dao.getCartItems(userId);
	}



	@Override
	public boolean addBookToCart(long userId, long bookId) {
		
		return dao.addBookToCart(userId, bookId);
	}



	@Override
	public boolean removeBookFromCart(Long userId, long bookId) {
		// TODO Auto-generated method stub
		return dao.removeBookFromCart(userId,bookId);
	}

    
}
