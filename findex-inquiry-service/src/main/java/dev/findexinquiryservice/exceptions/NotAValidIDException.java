package dev.findexinquiryservice.exceptions;

/**
 * It is thrown when the ID ends up with an odd number.
 * @author Erhan CAVDAR
 */
public class NotAValidIDException extends RuntimeException {
    public NotAValidIDException(String message) {
        super(message);
    }
}
