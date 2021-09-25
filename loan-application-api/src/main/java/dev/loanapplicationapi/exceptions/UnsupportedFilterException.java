package dev.loanapplicationapi.exceptions;

public class UnsupportedFilterException extends RuntimeException{
    public UnsupportedFilterException(String message) {
        super(message);
    }
}
