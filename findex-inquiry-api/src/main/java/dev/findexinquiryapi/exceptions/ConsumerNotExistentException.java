package dev.findexinquiryapi.exceptions;
/**
 * It is thrown if the Consumer DOES NOT exists with the given criteria which might be (and generally it is)
 * national ID of the consumer.
 * @author Erhan CAVDAR
 */
public class ConsumerNotExistentException extends RuntimeException {
    public ConsumerNotExistentException(String message) {
        super(message);
    }
}
