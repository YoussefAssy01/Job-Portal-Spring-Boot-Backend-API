package org.joe.jobpoertalapp.dtos.incoming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InApplicationDto(@NotNull Long jobId, @NotNull Long jobSeekerId) {
}
