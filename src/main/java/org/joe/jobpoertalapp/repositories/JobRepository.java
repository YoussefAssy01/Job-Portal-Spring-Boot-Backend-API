package org.joe.jobpoertalapp.repositories;

import org.joe.jobpoertalapp.entities.Job;
import org.joe.jobpoertalapp.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
    List<Job> findByStatusAndTitle(JobStatus status, String title);
    List<Job> findByStatusAndLocationAndTitle(JobStatus status, String location, String title);
    List<Job> findByStatusAndLocationAndTitleAndPostedAtAfter(JobStatus status, String location, String title, LocalDateTime date);
}
