package umb.khh.edudate.exception;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String message, HttpStatus badRequest) {
        super(message);
    }
}

