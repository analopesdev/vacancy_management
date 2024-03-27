package br.com.analopesdev.vacancy_management.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.analopesdev.vacancy_management.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Long>{
 
}
