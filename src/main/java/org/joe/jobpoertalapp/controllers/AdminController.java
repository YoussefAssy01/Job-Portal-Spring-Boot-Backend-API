package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<OutJobDto>> getJobs(){
        List<OutJobDto> jobs = adminService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/pending")
    public ResponseEntity<List<OutJobDto>> getPendingJobs(){
        List<OutJobDto> jobs = adminService.getPendingJobs();
        return ResponseEntity.ok(jobs);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        adminService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jobs/{id}/approve")
    public ResponseEntity<OutJobDto> approveJob(@PathVariable Long id){
        return ResponseEntity.ok(adminService.approveJob(id));
    }

    @PutMapping("/jobs/{id}/reject")
    public ResponseEntity<OutJobDto> rejectJob(@PathVariable Long id){
        return ResponseEntity.ok(adminService.rejectJob(id));
    }
}
