package org.joe.jobpoertalapp.filters;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.joe.jobpoertalapp.services.CustomUserDetailsService;
import org.joe.jobpoertalapp.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    public JwtAuthFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token;
        String username;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
             token = authHeader.substring(7);
             try{
                 username = jwtUtil.extractUsername(token);
             }catch(ExpiredJwtException e){
                 System.out.println("JWT Token is expired");
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 response.getWriter().write("JWT token expired");
                 filterChain.doFilter(request,response);
                 return ;
             }

             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                 UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                 if (jwtUtil.validateToken(token)){
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 }
             }
        }
        filterChain.doFilter(request,response);
    }
}
