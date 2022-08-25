package org.aibles.carservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author toanns
 */
public class InternalServerException extends BaseException {

  public InternalServerException(String error) {
    setCode("org.aibles.carservice.exception.InternalServerException");
    setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    addParam("error",error);
  }
}
