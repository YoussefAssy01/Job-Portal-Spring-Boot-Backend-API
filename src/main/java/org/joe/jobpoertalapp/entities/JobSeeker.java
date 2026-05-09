package org.joe.jobpoertalapp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "job_seeker")
@PrimaryKeyJoinColumn(name = "id")
public class JobSeeker extends User {
    private String resumeLink;
    @OneToMany(mappedBy = "jobSeeker")
    private List<Application> applications;

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
