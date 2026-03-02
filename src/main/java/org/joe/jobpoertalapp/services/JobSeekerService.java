package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<OutJobDto> getAvailableJobsByTitleAndLocationAndDate(String title, String location, LocalDateTime date){
        return jobService.getAvailableJobsByTitleAndLocationAndDate(title,location,date);
    }

    public List<OutApplicationDto> getMyApplications(Long jobSeekerId) {
        return applicationService.viewApplicationByJobSeekerId(jobSeekerId);
    }

    public void deleteApplication(Long id){
        applicationService.deleteApplication(id);
    }

    public void createApplication(InApplicationDto inApplicationDto){
        applicationService.createApplication(inApplicationDto);
    }

}
