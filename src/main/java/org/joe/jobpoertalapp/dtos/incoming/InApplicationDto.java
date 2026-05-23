package org.joe.jobpoertalapp.dtos.incoming;

import jakarta.validation.constraints.NotBlank;

public record InApplicationDto(@NotBlank Long jobId,@NotBlank Long jobSeekerId) {
}
