package com.web.Controller;

import com.web.Entity.Book;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.RegisterServiceImpl;
import com.web.ServiceInterface.RegisterServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/AddBookServlet")
public class RegisterBook extends HttpServlet {
    private RegisterServiceInterface service;

    public RegisterBook() {
        service = GenericConfiguration.createInstance(RegisterServiceImpl.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the retailerId from the session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Long retailerId = (Long) session.getAttribute("userId");

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String publicationDateStr = request.getParameter("publicationDate");
        String genre = request.getParameter("genre");
        String language = request.getParameter("language");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String description = request.getParameter("description");
        String coverImageUrl = request.getParameter("coverImageUrl");

        // Convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate publicationDate = LocalDate.parse(publicationDateStr, formatter);

        // Create a new Book object
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublicationDate(publicationDate);
        book.setGenre(genre);
        book.setLanguage(language);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setDescription(description);
        book.setCoverImageUrl(coverImageUrl);

        // Register the book using the service, passing the retailerId
        String msg = service.registerBook(book, retailerId);

        // Redirect to the success page with the book title
        response.sendRedirect("successbookRegistry.jsp?message=" + URLEncoder.encode("Book \"" + book.getTitle() + "\" registered successfully!", "UTF-8"));
    }
}
