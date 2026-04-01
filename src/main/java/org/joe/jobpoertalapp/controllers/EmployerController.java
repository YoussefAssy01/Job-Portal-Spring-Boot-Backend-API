package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.incoming.InJobDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    private final EmployerService employerService;
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/{id}/jobs")
    public List<OutJobDto> getJobsForEmployer(@PathVariable Long id){
        return employerService.getMyJobs(id);
    }

    @GetMapping("/jobs/{id}/applications")
    public List<OutApplicationDto> getApplicationsByJobId(@PathVariable Long id){
        return employerService.getApplicationsByJobId(id);
    }

    @PutMapping("/applications/{id}/approve")
    public void approveApplication(@PathVariable Long id){
        employerService.approveApplication(id);
    }

    @PutMapping("/applications/{id}/reject")
    public void rejectApplication(@PathVariable Long id){
        employerService.rejectApplication(id);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id){
        employerService.deleteJob(id);
    }

    @PostMapping("/{id}/jobs")
    public void postJob(@RequestBody InJobDto inJobDto, @PathVariable Long id){
        employerService.postJob(inJobDto, id);
    }


}
