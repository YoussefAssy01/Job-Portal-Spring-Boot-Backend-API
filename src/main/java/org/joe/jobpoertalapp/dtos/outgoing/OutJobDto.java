package org.joe.jobpoertalapp.dtos.outgoing;

import org.joe.jobpoertalapp.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OutJobDto(Long id, String title, String description, String location,
                        BigDecimal salary, Status status, LocalDateTime postedAt,Long EmployerId) {
}
