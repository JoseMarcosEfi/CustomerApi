package com.api.customer.enums;

public enum Type {
    TOOL(0),
    PART(1),
    OTHERS(2);

    private int typeCode;

    private Type(int type) {
        this.typeCode = type;
    }

    public int getType() {
        return typeCode;
    }

    public static Type valueOf(int typeCode) {
        for (Type value : Type.values()) {
            if (typeCode == value.getType()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Type Code");
    }
}
