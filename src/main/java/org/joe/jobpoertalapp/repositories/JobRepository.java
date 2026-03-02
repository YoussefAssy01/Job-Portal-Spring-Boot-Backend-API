package org.joe.jobpoertalapp.repositories;

import org.joe.jobpoertalapp.entities.Job;
import org.joe.jobpoertalapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(Status status);
    List<Job> findByStatusAndTitle(Status status, String title);
    List<Job> findByStatusAndLocationAndTitle(Status status, String location, String title);
    List<Job> findByStatusAndLocationAndTitleAndPostedAtAfter(Status status, String location, String title, LocalDateTime date);
    List<Job> findByEmployerId(Long id);
    @Query("select j from Job j order by j.status")
    List<Job> findAllOrderByStatusAsc();
}
