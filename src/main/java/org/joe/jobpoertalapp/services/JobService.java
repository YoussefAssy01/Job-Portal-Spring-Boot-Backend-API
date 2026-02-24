package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.entities.Job;
import org.joe.jobpoertalapp.enums.Status;
import org.joe.jobpoertalapp.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {
    JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public List<Job> findAllByStatus(Status status) {
        return this.jobRepository.findByStatus(status);
    }

    public List<Job> findByEmployerId(Long employerId) {
        return this.jobRepository.findByEmployerId(employerId);
    }

    public Job findById(Long id) {
        return this.jobRepository.findById(id).orElseThrow();
    }

    public List<Job> findByStatusAndTitle(Status status, String title) {
        return this.jobRepository.findByStatusAndTitle(status, title);
    }

    public List<Job> findByStatusAndLocationAndTitle(Status status, String location, String title) {
        return this.jobRepository.findByStatusAndLocationAndTitle(status, location, title);
    }

    public List<Job> findByStatusAndLocationAndTitleAndPostedAtAfter(Status status, String location, String title, LocalDateTime date) {
        return this.jobRepository.findByStatusAndLocationAndTitleAndPostedAtAfter(status, location, title, date);
    }

    public Job save(Job job) {
        return this.jobRepository.save(job);
    }

    public void deleteById(Long id) {
        this.jobRepository.deleteById(id);
    }

}
