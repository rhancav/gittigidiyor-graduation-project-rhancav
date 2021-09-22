package dev.findexinquiryapi.exceptions;

public class ConsumerAlreadyExistsException extends RuntimeException {
    public ConsumerAlreadyExistsException(String message) {
        super(message);
    }
}
