package com.web.Controller;

import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.BookServiceImpl;
import com.web.ServiceInterface.BookServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookServiceInterface bookService ; 
    public SearchServlet() {
    	this.bookService=GenericConfiguration.createInstance(BookServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Book> books = bookService.searchBooks(query); // Implement searchBooks in BookService

        request.setAttribute("books", books);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}
