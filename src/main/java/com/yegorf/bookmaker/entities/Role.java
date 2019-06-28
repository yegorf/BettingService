package com.yegorf.bookmaker.entities;

public enum Role {
    USER (0),
    ADMIN (1);

    private int code;
    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
