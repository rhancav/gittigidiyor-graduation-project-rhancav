package dev.loanapplicationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundAnyApplicationsException extends RuntimeException{
    public NotFoundAnyApplicationsException(String message) {
        super(message);
    }
}
