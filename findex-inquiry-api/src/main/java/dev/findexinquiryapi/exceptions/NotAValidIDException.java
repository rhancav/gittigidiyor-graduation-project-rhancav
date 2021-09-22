package dev.findexinquiryapi.exceptions;

public class NotAValidIDException extends RuntimeException{
    public NotAValidIDException(String message) {
        super(message);
    }
}
