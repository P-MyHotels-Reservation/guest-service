package com.example.guestservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

  private final GuestExceptionConverter accountExceptionConverter = new GuestExceptionConverter();

  @ExceptionHandler(GuestServiceException.class)
  public ResponseEntity<Object> handleAccountNotFoundException(GuestServiceException ex) {
    log.error("AccountException", ex);

    return new ResponseEntity<>(
        accountExceptionConverter.toJsonNode(ex.getGuestErrorResponse(), StringUtils.EMPTY),
        new HttpHeaders(), ex.getGuestErrorResponse().getHttpStatus());
  }
}
