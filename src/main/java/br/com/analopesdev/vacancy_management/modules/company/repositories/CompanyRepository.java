package br.com.analopesdev.vacancy_management.modules.company.repositories;

import br.com.analopesdev.vacancy_management.modules.company.entities.CompanyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrName(String userName, String name);
  Optional<CompanyEntity> findByUsername(String userName);
}
