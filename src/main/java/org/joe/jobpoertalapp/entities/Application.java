package org.joe.jobpoertalapp.entities;

import jakarta.persistence.*;
import org.joe.jobpoertalapp.enums.Status;

@Entity
@Table(name = "application",schema = "dbo")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne()
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3,columnDefinition = "CHAR(3)")
    private Status status = Status.PEN; // default in Java

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", job=" + job +
                ", jobSeeker=" + jobSeeker +
                ", status='" + status + '\'' +
                '}';
    }
}
