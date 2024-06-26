package br.com.analopesdev.vacancy_management.modules.company.controllers;

import br.com.analopesdev.vacancy_management.modules.company.dto.AuthCompanyDto;
import br.com.analopesdev.vacancy_management.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> create(
    @RequestBody AuthCompanyDto authCompanyDto
  ) {
    try {
      var result = this.authCompanyUseCase.execute(authCompanyDto);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(e.getMessage());
    }
  }
}
