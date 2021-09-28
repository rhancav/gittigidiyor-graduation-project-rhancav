package dev.loanapplicationservice.exceptions;

import dev.loanapplicationservice.service.CreditApplicationLogService;

/**
 * Thrown when a unsupported filter is passed in {@link CreditApplicationLogService} findAllByID()
 * method.
 * @author Erhan Cavdar
 */
public class UnsupportedFilterException extends RuntimeException{
    public UnsupportedFilterException(String message) {
        super(message);
    }
}
