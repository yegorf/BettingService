package com.yegorf.bookmaker.validators;

public interface ValidationStrategy {
    void check(String text) throws Exception;
}
