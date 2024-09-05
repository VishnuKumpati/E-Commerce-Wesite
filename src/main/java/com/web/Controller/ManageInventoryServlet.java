package com.web.Controller;

import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.BookServiceImpl;
import com.web.ServiceInterface.BookServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageInventoryServlet")
public class ManageInventoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookServiceInterface bookService;

    public ManageInventoryServlet() {
        bookService = GenericConfiguration.createInstance(BookServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
            return;
        }

        Long retailerId = (Long) session.getAttribute("userId");
        List<Book> books = bookService.getBooksByRetailer(retailerId);
        System.out.println(books);
        request.setAttribute("books", books);
        request.setAttribute("retailerId", retailerId); // Pass retailerId to JSP
        request.getRequestDispatcher("ManageInventory.jsp").forward(request, response);
    }
}
