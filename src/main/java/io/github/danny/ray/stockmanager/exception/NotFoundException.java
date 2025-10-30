package io.github.danny.ray.stockmanager.exception;

public class NotFoundException extends FetchException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
