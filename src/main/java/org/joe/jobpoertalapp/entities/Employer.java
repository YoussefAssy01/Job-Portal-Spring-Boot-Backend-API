package org.joe.jobpoertalapp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employer")
@PrimaryKeyJoinColumn(name = "id")
public class Employer extends User{
    @Column(nullable = false)
    private String companyName;
    private String info;
    @OneToMany(mappedBy = "employer",fetch = FetchType.LAZY)
    private List<Job> jobs;
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
