package org.joe.jobpoertalapp.dtos.incoming;

import org.joe.jobpoertalapp.enums.Role;

public record InSignupRequest(String username, String password, String email, String name, Role role, String resumeLink, String companyName, String info) {
}
