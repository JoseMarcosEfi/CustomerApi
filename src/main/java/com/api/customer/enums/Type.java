package com.api.customer.enums;

public enum Type {
    TOOL(0),
    PART(1),
    OTHERS(2);

    private int Type;

    private Type(int type) {
        this.Type = type;
    }

    public int getTipo() {
        return Type;
    }

}
