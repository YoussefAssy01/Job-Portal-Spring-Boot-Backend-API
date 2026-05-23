package org.joe.jobpoertalapp.exceptions;

public class AuthenticationException extends BaseException {
    public AuthenticationException(String message) {
        super(message, "AUTH_FAILED");
    }
}
