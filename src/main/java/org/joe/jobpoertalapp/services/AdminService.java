package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;

import java.util.List;

public class AdminService {
    private JobService jobService;
    public AdminService(JobService jobService) {
        this.jobService = jobService;
    }

    public void approveJob(Long jobId){
        jobService.approveJob(jobId);
    }

    public void rejectJob(Long jobId){
        jobService.rejectJob(jobId);
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
