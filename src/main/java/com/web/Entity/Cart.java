package com.web.Entity;

public class Cart {
    private Long id;
    private Long userId;
    private Long bookId;
    private int quantity;

    public Cart() {
    }

    public Cart(Long id, Long userId, Long bookId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
    }
}
