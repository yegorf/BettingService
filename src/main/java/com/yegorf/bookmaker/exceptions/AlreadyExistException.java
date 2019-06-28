package com.yegorf.bookmaker.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistException extends Exception {
    public AlreadyExistException(String message) {
        super(message);
    }
}
