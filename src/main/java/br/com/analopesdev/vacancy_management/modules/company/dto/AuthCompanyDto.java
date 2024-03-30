package br.com.analopesdev.vacancy_management.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDto {

  private String username;
  private String password;
}
