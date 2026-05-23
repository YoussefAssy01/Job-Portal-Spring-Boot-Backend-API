package org.joe.jobpoertalapp.exceptions;

public class InternalServerException extends BaseException {
    public InternalServerException() {
        super("Unexpected error", "INTERNAL_ERROR");
    }
}
