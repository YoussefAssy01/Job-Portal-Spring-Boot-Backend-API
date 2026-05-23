package org.joe.jobpoertalapp.exceptions;

public abstract class BaseException extends RuntimeException {

    private final String message;
    private final String code;

    protected BaseException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
