package br.com.analopesdev.vacancy_management.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.analopesdev.vacancy_management.exceptions.UserFoundException;
import br.com.analopesdev.vacancy_management.modules.candidate.CandidateEntity;
import br.com.analopesdev.vacancy_management.modules.candidate.CandidateRepository;


@Service
public class CreateCandidateUseCase {

  @Autowired
  private PasswordEncoder passwordEncoder;
    
  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity){
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail()).ifPresent((user) -> {
      throw new UserFoundException();
    });

    var password = this.passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }
}
