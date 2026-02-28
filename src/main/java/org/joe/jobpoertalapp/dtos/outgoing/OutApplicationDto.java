package org.joe.jobpoertalapp.dtos.outgoing;

import org.joe.jobpoertalapp.enums.Status;

public record OutApplicationDto(Long id, Long jobId, Long jobSeekerId, Status status) {
}
