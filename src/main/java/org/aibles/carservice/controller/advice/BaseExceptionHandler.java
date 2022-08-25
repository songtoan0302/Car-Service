package org.aibles.carservice.controller.advice;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.exceptions.BaseException;
import org.aibles.carservice.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ToanNS
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
  @ExceptionHandler(value = {BaseException.class})
  public ResponseEntity<ExceptionResponse> baseExceptionHandler(BaseException error) {
    log.error("(Exception) error: {}", error);
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Exception!");
    exceptionResponse.setMessage(error.getMessage());
    exceptionResponse.setTimestamp(Instant.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(error.getStatus()));
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse validationExceptionHandler() {
    log.error("(Validation) error.");
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setError("Exception!");
    exceptionResponse.setMessage("Data input invalid");
    exceptionResponse.setTimestamp(Instant.now());
    return exceptionResponse;
  }
}
