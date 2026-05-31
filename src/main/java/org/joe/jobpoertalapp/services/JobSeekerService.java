package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.entities.Application;
import org.joe.jobpoertalapp.entities.User;
import org.joe.jobpoertalapp.exceptions.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class JobSeekerService {
    private JobService jobService;
    private ApplicationService applicationService;
    public JobSeekerService(JobService jobService, ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    public List<OutJobDto> getAvailableJobs() {
        return jobService.getAvailableJobs();
    }

    public List<OutJobDto> getAvailableJobsByTitle(String title){
        return jobService.getAvailableJobsByTitle(title);
    }

    public List<OutJobDto> getAvailableJobsByTitleAndLocation(String title,String location){
        return jobService.getAvailableJobsByTitleAndLocation(title,location);
    }

    public List<OutApplicationDto> getMyApplications() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();
        return applicationService.viewApplicationByJobSeekerId(userId);
    }

    public void deleteApplication(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();

        Application application = applicationService.getApplicationById(id);

        if(!Objects.equals(application.getJobSeeker().getId(),userId)){
            throw new AccessDeniedException("Access denied");
        }

        applicationService.deleteApplication(id);
    }

    public OutApplicationDto createApplication(InApplicationDto inApplicationDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();


        return applicationService.createApplication(inApplicationDto.jobId(),userId);
    }

}
