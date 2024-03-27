package br.com.analopesdev.vacancy_management.modules.company.entities;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "job")
@Data
public class JobEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String description;
  private String level;
  private String benefits;
  private UUID companyId;
  
  @CreationTimestamp
  private java.sql.Timestamp createdAt;
  
}
