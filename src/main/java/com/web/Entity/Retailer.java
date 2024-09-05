package com.web.Entity;

import java.util.List;

public class Retailer {
    private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private String city;
    private String password;
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private List<Book> books;

    // Constructors
    public Retailer() {}

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Retailer(String name, String email, String contactNumber) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
     
      
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long i) {
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
