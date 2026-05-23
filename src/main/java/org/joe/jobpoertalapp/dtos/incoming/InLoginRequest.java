package org.joe.jobpoertalapp.dtos.incoming;

import jakarta.validation.constraints.NotBlank;

public record InLoginRequest(@NotBlank String username,@NotBlank String password) {
}
