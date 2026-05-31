package org.joe.jobpoertalapp.services;

import org.joe.jobpoertalapp.dtos.incoming.InApplicationDto;
import org.joe.jobpoertalapp.dtos.outgoing.OutApplicationDto;
import org.joe.jobpoertalapp.entities.Application;
import org.joe.jobpoertalapp.enums.Status;
import org.joe.jobpoertalapp.exceptions.ResourceNotFoundException;
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

    public Application getApplicationById(Long id){
        return applicationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Application not found"));
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
    public OutApplicationDto approveApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id+" of application not found"));
        application.setStatus(Status.APP);
        return mapEntityToDto(application);
    }

    @Transactional
    public OutApplicationDto rejectApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id+" of application not found"));
        application.setStatus(Status.REJ);
        return mapEntityToDto(application);
    }

    public void deleteApplication(Long id) {
        if(applicationRepository.existsById(id))
            applicationRepository.deleteById(id);
    }

    @Transactional
    public OutApplicationDto createApplication(Long jobId,Long jobSeekerId) {
        Application application = new Application();
        application.setStatus(Status.PEN);
        application.setJob(jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException(jobId+" of job not found")));
        application.setJobSeeker(jobSeekerRepository.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException(jobSeekerId+" of jobseeker not found")));
        applicationRepository.save(application);
        return mapEntityToDto(application);
    }


    private OutApplicationDto mapEntityToDto(Application application) {
        return new OutApplicationDto(application.getId()
                ,application.getJob().getId(),application.getJobSeeker().getId(),application.getStatus());
    }
}
