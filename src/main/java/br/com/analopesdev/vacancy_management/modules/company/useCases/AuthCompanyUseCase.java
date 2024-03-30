package br.com.analopesdev.vacancy_management.modules.company.useCases;

import br.com.analopesdev.vacancy_management.modules.company.dto.AuthCompanyDto;
import br.com.analopesdev.vacancy_management.modules.company.dto.AuthCompanyResponseDTO;
import br.com.analopesdev.vacancy_management.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import javax.security.sasl.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCompanyResponseDTO execute(AuthCompanyDto authCompanyDto)
    throws AuthenticationException {
    var company =
      this.companyRepository.findByUsername(authCompanyDto.getUsername())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("username/password incorrect");
        });

    var passwordMatches =
      this.passwordEncoder.matches(
          authCompanyDto.getPassword(),
          company.getPassword()
        );

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    var expiresIn = Instant.now().plus(Duration.ofHours(2));

    var token = JWT
      .create()
      .withIssuer("auth0")
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withSubject(company.getId().toString())
      .withExpiresAt(expiresIn)
      .withClaim("roles", Arrays.asList("COMPANY"))
      .sign(algorithm);

    var authCompanyResponseDTO = AuthCompanyResponseDTO
      .builder()
      .access_token(token)
      .expires_in(expiresIn.toEpochMilli())
      .build();

    return authCompanyResponseDTO;
  }
}
