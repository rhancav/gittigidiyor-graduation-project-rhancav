package dev.loanapplicationapi.exceptions;

public class NotFoundAnyApplicationsException extends RuntimeException{
    public NotFoundAnyApplicationsException(String message) {
        super(message);
    }
}
