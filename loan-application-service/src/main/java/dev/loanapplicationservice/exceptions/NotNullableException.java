package dev.loanapplicationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a "not nullable" argument is passed.
 * @author Erhan Cavdar
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotNullableException extends RuntimeException{
    public NotNullableException(String message) {
        super(message);
    }
}
