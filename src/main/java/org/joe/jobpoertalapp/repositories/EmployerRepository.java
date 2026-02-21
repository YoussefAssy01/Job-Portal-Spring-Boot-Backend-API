package org.joe.jobpoertalapp.repositories;

import org.joe.jobpoertalapp.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    Employer findByEmail(String email);

}
