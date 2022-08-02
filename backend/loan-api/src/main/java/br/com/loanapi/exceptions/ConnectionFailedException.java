package br.com.loanapi.exceptions;

public class ConnectionFailedException extends RuntimeException {

    public ConnectionFailedException(String message) {
        super(message);
    }

}
