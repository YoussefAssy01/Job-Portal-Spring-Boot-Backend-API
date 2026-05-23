package org.joe.jobpoertalapp.exceptions;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException(String message) {
        super(message, "ACCESS_DENIED");
    }
}
