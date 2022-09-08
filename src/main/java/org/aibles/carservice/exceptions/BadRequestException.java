package org.aibles.carservice.exceptions;


import org.springframework.http.HttpStatus;

/**
 * @author toanns
 */
public class BadRequestException extends BaseException{

  public BadRequestException(Object dataInput) {
    setCode("org.aibles.carservice.exception.BadRequestException");
    setStatus(HttpStatus.BAD_REQUEST.value());
    addParam("data invalid",dataInput);
  }
}
