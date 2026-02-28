package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.entities.Application;
import org.joe.jobpoertalapp.enums.Status;
import org.joe.jobpoertalapp.repositories.ApplicationRepository;
import org.joe.jobpoertalapp.repositories.JobRepository;
import org.joe.jobpoertalapp.repositories.JobSeekerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepository;
    private JobRepository jobRepository;
    private JobSeekerRepository jobSeekerRepository;

    public ApplicationService(ApplicationRepository applicationRepository,JobRepository jobRepository,JobSeekerRepository jobSeekerRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public List<OutApplicationDto> viewApplicationByJobId(Long jobId) {
        return applicationRepository.findAllByJobIdSortedByStatus(jobId).stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public List<OutApplicationDto> viewApplicationByJobSeekerId(Long jobSeekerId) {
        return applicationRepository.findAllByJobSeekerIdSortedByStatus(jobSeekerId).stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Transactional
    public void approveApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(Status.APP);
    }

    @Transactional
    public void rejectApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(Status.REJ);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Transactional
    public void createApplication(InApplicationDto inApplicationDto) {
        Application application = new Application();
        application.setStatus(Status.PEN);
        application.setJob(jobRepository.findById(inApplicationDto.jobId()).orElseThrow());
        application.setJobSeeker(jobSeekerRepository.findById(inApplicationDto.jobSeekerId()).orElseThrow());
        applicationRepository.save(application);
    }


    private OutApplicationDto mapEntityToDto(Application application) {
        return new OutApplicationDto(application.getId()
                ,application.getJob().getId(),application.getJobSeeker().getId(),application.getStatus());
    }
}
