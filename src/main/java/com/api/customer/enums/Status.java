package com.api.customer.enums;

public enum Status {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private int statusCode;

    private Status(int status) {
        this.statusCode = status;
    }

    public int getStatus() {
        return statusCode;
    }

    public static Status valueOf(int statusCode) {
        for (Status value : Status.values()) {
            if (statusCode == value.getStatus()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Status Code");
    }
}
