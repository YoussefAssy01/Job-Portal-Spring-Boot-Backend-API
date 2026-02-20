package org.joe.jobpoertalapp.entities;

import jakarta.persistence.*;
import org.joe.jobpoertalapp.enums.JobStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job",schema = "dbo")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(precision = 12, scale = 2)
    private BigDecimal salary;
    @Column(nullable = false, length = 3,columnDefinition = "CHAR(3)")
    private JobStatus status = JobStatus.PEN;
    @Column(nullable = false)
    private LocalDateTime postedAt = LocalDateTime.now();
    @ManyToOne()
    @JoinColumn(name = "employer_id",nullable = false)
    private Employer employer;

    @OneToMany(mappedBy = "job")
    private List<Application> applications;

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", postedAt=" + postedAt +
                ", employer=" + employer +
                ", applications=" + applications +
                '}';
    }
}
