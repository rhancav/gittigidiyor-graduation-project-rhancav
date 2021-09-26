package dev.findexinquiryapi.exceptions;

public class NotNullableException extends RuntimeException{
    public NotNullableException(String message) {
        super(message);
    }
}
