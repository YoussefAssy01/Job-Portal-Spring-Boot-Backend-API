package org.joe.jobpoertalapp.dtos.incoming;

import java.math.BigDecimal;

public record InJobDto(String title, String description, String location, BigDecimal salary) {
}
