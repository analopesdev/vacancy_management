package br.com.analopesdev.vacancy_management.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
  private UUID id;
  private String name;

  @Pattern(regexp = "\\S+", message = "Username should not be empty")
  private String username;

  @Email(message = "Email should be valid")
  private String email;

  @Length(min = 10, max = 100)
  private String password;

  @Length(min = 10, max = 255)
  private String description;

  private String curriculum;
}
