package org.joe.jobpoertalapp.dtos.incoming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InJobDto(@NotBlank String title,@NotBlank String description,@NotBlank String location,@NotNull BigDecimal salary) {
}
