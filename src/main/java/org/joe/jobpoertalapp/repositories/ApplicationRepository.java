package org.joe.jobpoertalapp.repositories;

import org.joe.jobpoertalapp.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.job.id = :jobId ORDER BY a.status")
    List<Application> findAllByJobIdSortedByStatus(@Param("jobId") Long jobId);

    @Query("SELECT a FROM Application a WHERE a.jobSeeker.id = :jobSeekerId ORDER BY a.status")
    List<Application> findAllByJobSeekerIdSortedByStatus(@Param("jobSeekerId") Long jobSeekerId);
}
