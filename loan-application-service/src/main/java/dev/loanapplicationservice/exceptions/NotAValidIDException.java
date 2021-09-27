package dev.loanapplicationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the id is in invalid format(ends with an odd number or shorter then expected).
 * @author Erhan Cavdar
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAValidIDException extends RuntimeException{
    public NotAValidIDException(String message) {
        super(message);
    }
}
