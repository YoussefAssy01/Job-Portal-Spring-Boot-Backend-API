package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.incoming.InLoginRequest;
import org.joe.jobpoertalapp.dtos.incoming.InSignupRequest;
import org.joe.jobpoertalapp.dtos.outgoing.OutSignupRequest;
import org.joe.jobpoertalapp.entities.User;
import org.joe.jobpoertalapp.services.CustomUserDetailsService;
import org.joe.jobpoertalapp.util.JWTUtil;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
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
    private final CustomUserDetailsService customUserDetailsService;
    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil,CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody @NonNull InLoginRequest request) {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            String username = authentication.getName();

            String role = authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();
            Long id = ((User)authentication.getPrincipal()).getId();
            return ResponseEntity.ok(jwtUtil.generateToken(username, id, role));

    }
    @PostMapping("/signup")
    public ResponseEntity<OutSignupRequest> signup(@RequestBody InSignupRequest request) {
        return ResponseEntity.ok(customUserDetailsService.addUser(request));
    };

}