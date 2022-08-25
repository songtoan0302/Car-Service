package org.aibles.carservice.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author toanns
 */
public class NotFoundException extends BaseException {

  public NotFoundException(String id) {
    setStatus(HttpStatus.NOT_FOUND.value());
    setCode("org.aibles.carservice.exception.NotFoundException");
    addParam("id",id);
  }
}
