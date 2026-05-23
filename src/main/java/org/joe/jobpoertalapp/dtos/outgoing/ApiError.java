package org.joe.jobpoertalapp.dtos.outgoing;

public record ApiError(String code,String message, int status) {
}
