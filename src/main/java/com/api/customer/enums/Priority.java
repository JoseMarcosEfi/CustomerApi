package com.api.customer.enums;

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private int priorityCode;

    private Priority(int priority) {
        this.priorityCode = priority;
    }

    public int getPriority() {
        return priorityCode;
    }

    public static Priority valueOf(int priorityCode) {
        for (Priority value : Priority.values()) {
            if (priorityCode == value.getPriority()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Priority Code");
    }
}
