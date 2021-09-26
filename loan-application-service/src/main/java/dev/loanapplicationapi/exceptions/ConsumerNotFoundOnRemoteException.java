package dev.loanapplicationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the customer is not found on the remote server(Findex Inquiry Api).
 * @author Erhan Cavdar
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConsumerNotFoundOnRemoteException extends RuntimeException{
    public ConsumerNotFoundOnRemoteException(String message) {
        super(message);
    }
}
