package br.com.analopesdev.vacancy_management.modules.company.entities;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Represents a company entity in the vacancy management system.
 */
@Entity(name = "company")

@Data
public class CompanyEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
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

  private String website;

  @CreationTimestamp
  private java.sql.Timestamp createdAt; 
}
