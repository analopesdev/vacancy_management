package br.com.analopesdev.vacancy_management.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.analopesdev.vacancy_management.modules.candidate.CandidateRepository;
import br.com.analopesdev.vacancy_management.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.analopesdev.vacancy_management.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired 
  private PasswordEncoder passwordEncoder;

  @Value("${jwt.secret.candidate}")
  private String jwtSecret;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Candidate not found");
    });

   var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

   if(!passwordMatches){
     throw new AuthenticationException();
   }

   Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
   var expiresIn = Instant.now().plus(Duration.ofHours(2));

   var token = JWT.create()
   .withIssuer("auth0")
   .withSubject(candidate.getId().toString())
   .withClaim("roles", Arrays.asList("CANDIDATE"))
   .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
   .sign(algorithm);

   var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).expires_in(expiresIn.toEpochMilli()).build();
   return authCandidateResponse;
  }
}
