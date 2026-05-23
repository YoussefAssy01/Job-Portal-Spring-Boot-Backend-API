package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private JobService jobService;
    public AdminService(JobService jobService) {
        this.jobService = jobService;
    }

    public OutJobDto approveJob(Long jobId){
        return jobService.approveJob(jobId);
    }

    public OutJobDto rejectJob(Long jobId){
        return jobService.rejectJob(jobId);
    }

    public void deleteJob(Long jobId){
        jobService.deleteJob(jobId);
    }

    public List<OutJobDto> getAllJobs(){
        return jobService.getAllJobs();
    }

    public List<OutJobDto> getPendingJobs(){
        return jobService.getPendingJobs();
    }


}
