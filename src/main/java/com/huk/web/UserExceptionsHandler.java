package com.huk.web;

import com.huk.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    if (ex instanceof ValidationException){
      return handleExceptionInternal(ex,
                                     ex.getMessage(),
                                     new HttpHeaders(),
                                     HttpStatus.BAD_REQUEST,
                                     request);
    }
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(ex,
                                   bodyOfResponse,
                                   new HttpHeaders(),
                                   HttpStatus.CONFLICT,
                                   request);
  }
}
