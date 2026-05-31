package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InJobDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.entities.Application;
import org.joe.jobpoertalapp.entities.Job;
import org.joe.jobpoertalapp.entities.User;
import org.joe.jobpoertalapp.exceptions.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployerService {
    private JobService jobService;
    private ApplicationService applicationService;
    public EmployerService(JobService jobService, ApplicationService applicationService){
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    public OutJobDto postJob(InJobDto inJobDto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();

        return jobService.postJob(inJobDto,userId);
    }

    public void deleteJob(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();

        Job job = jobService.getJobById(id);
        if(!Objects.equals(job.getEmployer().getId(), userId)){
            throw new AccessDeniedException("Access denied");
        }
        jobService.deleteJob(id);
    }

    public List<OutJobDto> getMyJobs(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();

        Long userId = user.getId();
        return jobService.getJobsByEmployerId(userId);
    }

    public List<OutApplicationDto> getApplicationsByJobId(Long jobId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();

        Long userId = user.getId();

        Job job = jobService.getJobById(jobId);
        if (!Objects.equals(job.getEmployer().getId(), userId)){
            throw new AccessDeniedException("Access Denied");
        }
        return applicationService.viewApplicationByJobId(jobId);
    }

    public OutApplicationDto approveApplication(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();

        Long userId = user.getId();

        Application application = applicationService.getApplicationById(id);
        Job job = jobService.getJobById(application.getJob().getId());

        if (!Objects.equals(job.getEmployer().getId(), userId)){
            throw new AccessDeniedException("Access Denied");
        }

        return applicationService.approveApplication(id);
    }

    public OutApplicationDto rejectApplication(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) auth.getPrincipal();

        Long userId = user.getId();

        Application application = applicationService.getApplicationById(id);
        Job job = jobService.getJobById(application.getJob().getId());

        if (!Objects.equals(job.getEmployer().getId(), userId)){
            throw new AccessDeniedException("Access Denied");
        }

        return applicationService.rejectApplication(id);
    }

}
