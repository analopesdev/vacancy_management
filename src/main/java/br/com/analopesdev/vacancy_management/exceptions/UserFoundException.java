package br.com.analopesdev.vacancy_management.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException(){
    super("User already exists");
  }
}
