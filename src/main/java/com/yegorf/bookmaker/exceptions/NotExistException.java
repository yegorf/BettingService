package com.yegorf.bookmaker.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotExistException extends Exception {
    public NotExistException(String message) {
        super(message);
    }
}
