package com.api.customer.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Looks like something's been missed here buddy :C");
    }
}
