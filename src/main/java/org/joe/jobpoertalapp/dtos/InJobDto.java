package org.joe.jobpoertalapp.dtos;

import java.math.BigDecimal;

public record InJobDto(String title, String description, String location, BigDecimal salary) {
}
