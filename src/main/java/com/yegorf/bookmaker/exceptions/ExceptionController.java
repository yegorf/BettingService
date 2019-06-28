package com.yegorf.bookmaker.exceptions;

import com.yegorf.bookmaker.dto.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorInfo> alreadyExistExceptionHandler(AlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorInfo(ErrorCode.ALREADY_EXIST.name(), exception.getMessage())
                );
    }

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ErrorInfo> notExistExceptionHandler(NotExistException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorInfo(ErrorCode.NOT_EXIST.name(), exception.getMessage())
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorInfo(ErrorCode.UNEXPECTED_EXCEPTION.name(), exception.getMessage())
                );
    }
}
