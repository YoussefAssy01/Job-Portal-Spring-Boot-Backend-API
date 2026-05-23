package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InJobDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutJobDto;
import org.joe.jobpoertalapp.entities.Job;
import org.joe.jobpoertalapp.enums.Status;
import org.joe.jobpoertalapp.repositories.EmployerRepository;
import org.joe.jobpoertalapp.repositories.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {
    private JobRepository jobRepository;
    private EmployerRepository employerRepository;

    public JobService(JobRepository jobRepository,EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
    }

    public List<OutJobDto> getAllJobs() {
        return jobRepository.findAllOrderByStatusAsc()
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getAvailableJobs() {
        return jobRepository.findByStatus(Status.APP)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getPendingJobs() {
        return jobRepository.findByStatus(Status.PEN)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getAvailableJobsByTitle(String title) {
        return jobRepository.findByStatusAndTitle(Status.APP,title)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getAvailableJobsByTitleAndLocation(String title,String location) {
        return jobRepository.findByStatusAndLocationAndTitle(Status.APP,location,title)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getAvailableJobsByTitleAndLocationAndDate(String title,String location,LocalDateTime date) {
        return jobRepository.findByStatusAndLocationAndTitleAndPostedAtAfter(Status.APP,location,title,date)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutJobDto> getJobsByEmployerId(Long id) {
        return jobRepository.findByEmployerId(id)
                .stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Transactional
    public OutJobDto approveJob(Long id) {
        Job job =jobRepository.findById(id).orElseThrow();
        job.setStatus(Status.APP);
        return mapEntityToDto(job);
    }

    @Transactional
    public OutJobDto rejectJob(Long id) {
        Job job =jobRepository.findById(id).orElseThrow();
        job.setStatus(Status.REJ);
        return mapEntityToDto(job);
    }

    @Transactional
    public OutJobDto postJob(InJobDto inJobDto,Long employerId) {
        Job newJob = new Job();
        newJob.setTitle(inJobDto.title());
        newJob.setDescription(inJobDto.description());
        newJob.setLocation(inJobDto.location());
        newJob.setSalary(inJobDto.salary());
        newJob.setStatus(Status.PEN);
        newJob.setPostedAt(LocalDateTime.now());
        newJob.setEmployer(employerRepository.findById(employerId).orElseThrow());
        jobRepository.save(newJob);
        return mapEntityToDto(newJob);
    }

    private OutJobDto mapEntityToDto(Job job) {
        return new OutJobDto(job.getId(),job.getTitle(),job.getDescription(),job.getLocation()
        ,job.getSalary(),job.getStatus(),job.getPostedAt(),job.getEmployer().getId());
    }


}
