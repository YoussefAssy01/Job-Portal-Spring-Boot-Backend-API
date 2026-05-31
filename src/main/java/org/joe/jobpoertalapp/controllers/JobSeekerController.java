package org.joe.jobpoertalapp.controllers;

import jakarta.validation.Valid;
import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.JobSeekerService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;
    public JobSeekerController(JobSeekerService jobSeekerService){
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<OutJobDto>> getFilteredJobs(@RequestParam(required = false) String title,
                                          @RequestParam(required = false) String location){

        if (location!=null && title!=null){
            return ResponseEntity.ok(jobSeekerService.getAvailableJobsByTitleAndLocation(title, location));
        } else if (title!=null) {
            return ResponseEntity.ok(jobSeekerService.getAvailableJobsByTitle(title));
        }
        else {
            return ResponseEntity.ok(jobSeekerService.getAvailableJobs());
        }
    }
    @GetMapping("me/applications")
    public ResponseEntity<List<OutApplicationDto>> getApplications(){
        return ResponseEntity.ok(jobSeekerService.getMyApplications());
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id){
        jobSeekerService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/applications")
    public ResponseEntity<OutApplicationDto> createApplication(@RequestBody @NonNull @Valid InApplicationDto inApplicationDto){
        return ResponseEntity.ok(jobSeekerService.createApplication(inApplicationDto));
    }
}
