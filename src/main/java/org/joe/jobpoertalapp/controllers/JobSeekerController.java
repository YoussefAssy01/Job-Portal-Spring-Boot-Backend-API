package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.JobSeekerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;
    public JobSeekerController(JobSeekerService jobSeekerService){
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/jobs")
    public List<OutJobDto> getFilteredJobs( @RequestParam(required = false) String title,
                                            @RequestParam(required = false) String location){

        if (location!=null && title!=null){
            return jobSeekerService.getAvailableJobsByTitleAndLocation(title, location);
        } else if (title!=null) {
            return jobSeekerService.getAvailableJobsByTitle(title);
        }
        else {
            return jobSeekerService.getAvailableJobs();
        }
    }
    @GetMapping("{id}/applications")
    public List<OutApplicationDto> getApplications(@PathVariable Long id){
        return jobSeekerService.getMyApplications(id);
    }

    @DeleteMapping("/applications/{id}")
    public void deleteApplication(@PathVariable Long id){
        jobSeekerService.deleteApplication(id);
    }
    @PostMapping("/applications")
    public void createApplication(@RequestBody InApplicationDto inApplicationDto){
        jobSeekerService.createApplication(inApplicationDto);
    }
}
