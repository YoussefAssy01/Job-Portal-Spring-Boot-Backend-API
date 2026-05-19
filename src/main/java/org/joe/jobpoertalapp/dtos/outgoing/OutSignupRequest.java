package org.joe.jobpoertalapp.dtos.outgoing;

import org.joe.jobpoertalapp.enums.Role;

public record OutSignupRequest(String username, Long id, String email, String name, Role role) {
}
