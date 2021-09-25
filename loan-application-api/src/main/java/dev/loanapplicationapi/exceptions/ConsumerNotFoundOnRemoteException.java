package dev.loanapplicationapi.exceptions;

public class ConsumerNotFoundOnRemoteException extends RuntimeException{
    public ConsumerNotFoundOnRemoteException(String message) {
        super(message);
    }
}
