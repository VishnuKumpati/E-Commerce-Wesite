package com.web.Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publicationDate; // Changed to LocalDate
    private String genre;
    private String language;
    private double price;
    private int stockQuantity;
    private String description;
    private String coverImageUrl;

    public Book() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        if (publicationDate != null) {
            this.publicationDate = publicationDate.toLocalDate(); // Convert java.sql.Date to LocalDate
        } else {
            this.publicationDate = null;
        }
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

	public void setRetailerId(Long retailerId) {
		this.setRetailerId(retailerId);
		
	}
	  public String toString() {
	        return "Book{" +
	                "id=" + id +
	                ", isbn='" + isbn + '\'' +
	                ", title='" + title + '\'' +
	                ", author='" + author + '\'' +
	                ", publisher='" + publisher + '\'' +
	                ", publicationDate=" + publicationDate +
	                ", genre='" + genre + '\'' +
	                ", language='" + language + '\'' +
	                ", price=" + price +
	                ", stockQuantity=" + stockQuantity +
	                ", description='" + description + '\'' +
	                ", coverImageUrl='" + coverImageUrl + '\'' +
	                
	                '}';
	    }
}
