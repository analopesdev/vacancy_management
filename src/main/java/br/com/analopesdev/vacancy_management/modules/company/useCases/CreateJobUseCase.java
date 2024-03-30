package br.com.analopesdev.vacancy_management.modules.company.useCases;

import br.com.analopesdev.vacancy_management.modules.company.entities.JobEntity;
import br.com.analopesdev.vacancy_management.modules.company.repositories.CompanyRepository;
import br.com.analopesdev.vacancy_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;

  public JobEntity execute(JobEntity jobEntity) {
    var companyEntity =
      this.companyRepository.findById(jobEntity.getCompanyId());

    if (companyEntity.isEmpty()) {
      throw new UsernameNotFoundException("Company not found");
    }

    return this.jobRepository.save(jobEntity);
  }
}
