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

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookServiceInterface bookService;

    public EditBookServlet() {
        bookService = GenericConfiguration.createInstance(BookServiceImpl.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String coverImageUrl = request.getParameter("coverImageUrl"); // Retrieve the cover image URL

        // Create a Book object and set its properties
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setCoverImageUrl(coverImageUrl); // Set the cover image URL

        // Update the book in the database
        boolean isUpdated = bookService.updateBook(book);

        // Redirect back to the inventory page with a success message
        if (isUpdated) {
            request.getSession().setAttribute("message", "Book updated successfully!");
        } else {
            request.getSession().setAttribute("message", "Failed to update the book!");
        }

        response.sendRedirect("ManageInventoryServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.parseLong(request.getParameter("id"));
        Book book = bookService.getBookById(bookId);

        if (book != null) {
            request.setAttribute("bookId", book.getId());
            request.setAttribute("title", book.getTitle());
            request.setAttribute("author", book.getAuthor());
            request.setAttribute("price", book.getPrice());
            request.setAttribute("stockQuantity", book.getStockQuantity());
            request.setAttribute("coverImageUrl", book.getCoverImageUrl());
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "Book not found!");
            response.sendRedirect("ManageInventoryServlet");
        }
    }}