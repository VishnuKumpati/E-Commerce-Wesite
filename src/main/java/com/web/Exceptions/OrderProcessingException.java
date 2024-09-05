package com.web.Exceptions;

import java.sql.SQLException;

public class OrderProcessingException extends SQLException {

    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
