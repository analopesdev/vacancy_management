package br.com.analopesdev.vacancy_management.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
  Optional<CandidateEntity> findByUsernameOrEmail(String userName, String email);
  Optional<CandidateEntity> findByUsername(String userName);
}