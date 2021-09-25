package dev.loanapplicationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thworn when no application exists when queried with the consumers id.
 * @author Erhan Cavdar
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundAnyApplicationsException extends RuntimeException{
    public NotFoundAnyApplicationsException(String message) {
        super(message);
    }
}
