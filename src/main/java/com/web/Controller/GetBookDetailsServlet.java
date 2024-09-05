package com.web.Controller;



import com.web.Service.serviceImpl.BookServiceImpl;
import com.web.ServiceInterface.BookServiceInterface;
import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/getAllBooksServlet")
public class GetBookDetailsServlet extends HttpServlet {

    /**
	 * 
	 */
	private BookServiceInterface bookService;
	private static final long serialVersionUID = 1L;
	 public GetBookDetailsServlet() {
	        bookService = GenericConfiguration.createInstance(BookServiceImpl.class);
	    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        System.out.println(books);
        request.setAttribute("books", books);
        HttpSession session=request.getSession();
      
        request.getRequestDispatcher("buyerDashboard.jsp").forward(request, response);
    }
}
