package br.com.analopesdev.vacancy_management.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  // Handle exception when a validation fails from type MethodArgumentNotValidException
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<ErrorMessageDTO> dto = new ArrayList<>();
    
    e.getBindingResult().getFieldErrors().forEach(err -> {
    
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

      ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(message, err.getField());
      dto.add(errorMessageDTO);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }
}
