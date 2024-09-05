package com.web.Controller;

import com.web.Service.serviceImpl.BookServiceImpl;
import com.web.ServiceInterface.BookServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookServiceInterface bookService;

    public DeleteBookServlet() {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdParam = request.getParameter("id");
        String retailerIdParam = request.getParameter("retailerId");

        if (bookIdParam != null && retailerIdParam != null) {
            try {
                long bookId = Long.parseLong(bookIdParam);
                long retailerId = Long.parseLong(retailerIdParam);
                
                // Log parameters
                System.out.println("Deleting book with ID: " + bookId);
                
                // Delete the book
                boolean isDeleted = bookService.deleteBook(bookId);
                
                if (isDeleted) {
                    response.sendRedirect("ManageInventoryServlet");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the book.");
                }
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book or retailer ID.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID and retailer ID are required.");
        }
    }
}