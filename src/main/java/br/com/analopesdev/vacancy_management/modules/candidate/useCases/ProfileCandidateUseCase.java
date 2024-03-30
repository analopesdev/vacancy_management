package br.com.analopesdev.vacancy_management.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.analopesdev.vacancy_management.modules.candidate.CandidateRepository;
import br.com.analopesdev.vacancy_management.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID id){
    var candidate = this.candidateRepository.findById(id).orElseThrow(() -> {
      throw new UsernameNotFoundException("Candidate not found");
    });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
    .description(candidate.getDescription())
    .email(candidate.getEmail())
    .name(candidate.getName())
    .username(candidate.getUsername())
    .id(candidate.getId())
    .build();

    return candidateDTO;
  }
}
