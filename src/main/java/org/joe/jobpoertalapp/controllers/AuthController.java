package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.incoming.InAuthenticationRequest;
import org.joe.jobpoertalapp.entities.User;
import org.joe.jobpoertalapp.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public String generateToken(@RequestBody InAuthenticationRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            String username = authentication.getName();

            String role = authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();
            Long id = ((User)authentication.getPrincipal()).getId();
            return jwtUtil.generateToken(username, id, role);

        } catch (BadCredentialsException e) {
            return "Bad Credentials";
        }
    }
}