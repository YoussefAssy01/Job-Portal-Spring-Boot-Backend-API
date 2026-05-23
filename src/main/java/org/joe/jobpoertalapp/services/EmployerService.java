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

    public OutJobDto postJob(InJobDto inJobDto, Long employerId){
        return jobService.postJob(inJobDto,employerId);
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

    public OutApplicationDto approveApplication(Long id){
        return applicationService.approveApplication(id);
    }

    public OutApplicationDto rejectApplication(Long id){
        return applicationService.rejectApplication(id);
    }

}
