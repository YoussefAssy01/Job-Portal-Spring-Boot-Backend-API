package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InSignupRequest;
import org.joe.jobpoertalapp.dtos.outgoing.OutSignupRequest;
import org.joe.jobpoertalapp.entities.Admin;
import org.joe.jobpoertalapp.entities.Employer;
import org.joe.jobpoertalapp.entities.JobSeeker;
import org.joe.jobpoertalapp.entities.User;
import org.joe.jobpoertalapp.enums.Role;
import org.joe.jobpoertalapp.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailsService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" not found"));
    }

    public OutSignupRequest addUser(InSignupRequest request) {

        User user;

        if (request.role().equals(Role.ADMIN)) {
            user = new Admin();
        } else if (request.role().equals(Role.EMPLOYER)) {
            Employer employer = new Employer();
            employer.setInfo(request.info());
            employer.setCompanyName(request.companyName());
            user = employer;
        } else if (request.role().equals(Role.JOBSEEKER)) {
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setResumeLink(request.resumeLink());
            user = jobSeeker;
        }
        else {
            throw new IllegalArgumentException("invalid role");
        }
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setName(request.name());
        User newuser = userDetailsRepository.save(user);
        String authority = user.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        Role role = Role.valueOf(authority.replace("ROLE_", ""));
        return  new OutSignupRequest(newuser.getUsername(), newuser.getId(),
                newuser.getEmail(), newuser.getName(),role
                );
    }
}
