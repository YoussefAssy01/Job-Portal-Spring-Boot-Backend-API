package org.joe.jobpoertalapp.dtos.incoming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.joe.jobpoertalapp.enums.Role;

public record InSignupRequest(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotBlank String name, @NotNull Role role, @NotBlank String resumeLink, @NotBlank String companyName, @NotBlank String info) {
}
