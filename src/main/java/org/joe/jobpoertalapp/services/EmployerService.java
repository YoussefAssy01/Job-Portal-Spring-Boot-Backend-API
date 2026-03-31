package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InJobDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    private JobService jobService;
    private ApplicationService applicationService;
    public EmployerService(JobService jobService, ApplicationService applicationService){
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    public void postJob(InJobDto inJobDto, Long employerId){
        jobService.postJob(inJobDto,employerId);
    }

    public void deleteJob(Long id){
        jobService.deleteJob(id);
    }

    public List<OutJobDto> getMyJobs(Long id){
        return jobService.getJobsByEmployerId(id);
    }

    public List<OutApplicationDto> getApplicationsByJobId(Long jobId){
        return applicationService.viewApplicationByJobId(jobId);
    }

    public void approveApplication(Long id){
        applicationService.approveApplication(id);
    }

    public void rejectApplication(Long id){
        applicationService.rejectApplication(id);
    }

}
