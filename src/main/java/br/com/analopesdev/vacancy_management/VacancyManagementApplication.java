package br.com.analopesdev.vacancy_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Vacancy Management",
    version = "1.0.0",
    description = "API for managing vacancies"
  )
)
public class VacancyManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(VacancyManagementApplication.class, args);
  }
}
