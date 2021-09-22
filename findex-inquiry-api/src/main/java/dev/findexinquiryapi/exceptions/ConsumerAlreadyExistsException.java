package dev.findexinquiryapi.exceptions;

/**
 * It is thrown if the Consumer exists with the given criteria which might be (and generally it is)
 * national ID of the consumer.
 * @author Erhan CAVDAR
 */
public class ConsumerAlreadyExistsException extends RuntimeException {
    public ConsumerAlreadyExistsException(String message) {
        super(message);
    }
}
