package org.joe.jobpoertalapp.controllers;

import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.services.AdminService;
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
    public List<OutJobDto> getJobs(){
        return adminService.getAllJobs();
    }

    @GetMapping("/jobs/pending")
    public List<OutJobDto> getPendingJobs(){
        return adminService.getPendingJobs();
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id){
        adminService.deleteJob(id);
    }

    @PutMapping("/jobs/{id}/approve")
    public void approveJob(@PathVariable Long id){
        adminService.approveJob(id);
    }

    @PutMapping("/jobs/{id}/reject")
    public void rejectJob(@PathVariable Long id){
        adminService.rejectJob(id);
    }



}
