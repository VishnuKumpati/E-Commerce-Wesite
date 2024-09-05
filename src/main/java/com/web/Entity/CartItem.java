package com.web.Entity;

public class CartItem {

    private Book book;
    private Long bookId;
    private int quantity;  // Add a field for the quantity

    // Constructor that initializes the book and sets the quantity to 1 by default
    public CartItem(Book book) {
        this.book = book;
        this.bookId = book.getId();
        this.quantity = 1;  // Default quantity
    }
    
    // Constructor that allows specifying an initial quantity
    public CartItem(Book book, int quantity) {
        this.book = book;
        this.bookId = book.getId();
        this.quantity = quantity; // Set the specified quantity
    }

    public Book getBook() {
        return book;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity; // Getter for quantity
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity; // Setter for quantity
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "bookId=" + bookId +
                ", title='" + book.getTitle() + '\'' +
                ", author='" + book.getAuthor() + '\'' +
                ", price=" + book.getPrice() +
                ", quantity=" + quantity + // Include quantity in the string representation
                '}';
    }
}
