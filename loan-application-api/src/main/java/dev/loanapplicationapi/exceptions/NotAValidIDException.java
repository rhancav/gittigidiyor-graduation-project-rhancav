package dev.loanapplicationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAValidIDException extends RuntimeException{
    public NotAValidIDException(String message) {
        super(message);
    }
}
