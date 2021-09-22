package dev.findexinquiryapi.exceptions;

public class ConsumerNotExistentException extends RuntimeException{
    public ConsumerNotExistentException(String message) {
        super(message);
    }
}
