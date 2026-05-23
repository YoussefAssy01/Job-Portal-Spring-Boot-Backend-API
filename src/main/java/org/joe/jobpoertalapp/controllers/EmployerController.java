package org.joe.jobpoertalapp.controllers;

import jakarta.validation.Valid;
import org.joe.jobpoertalapp.dtos.incoming.InJobDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.EmployerService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    private final EmployerService employerService;
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/me/jobs")
    public ResponseEntity<List<OutJobDto>> getMyJobs(){
        return ResponseEntity.ok(employerService.getMyJobs());
    }

    @GetMapping("/jobs/{id}/applications")
    public ResponseEntity<List<OutApplicationDto>> getApplicationsByJobId(@PathVariable Long id){
        return ResponseEntity.ok(employerService.getApplicationsByJobId(id));
    }

    @PutMapping("/applications/{id}/approve")
    public ResponseEntity<OutApplicationDto> approveApplication(@PathVariable Long id){
        return ResponseEntity.ok(employerService.approveApplication(id));
    }

    @PutMapping("/applications/{id}/reject")
    public ResponseEntity<OutApplicationDto> rejectApplication(@PathVariable Long id){
        return ResponseEntity.ok(employerService.rejectApplication(id));
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        employerService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/jobs")
    public ResponseEntity<OutJobDto> postJob(@RequestBody @NonNull @Valid InJobDto inJobDto, @PathVariable Long id){
        return ResponseEntity.ok(employerService.postJob(inJobDto, id));
    }


}
