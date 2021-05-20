package com.example.guestservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public enum GuestErrorResponse implements Serializable {

  GUEST_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "1000", "Guest is not found.");

  private final HttpStatus httpStatus;
  private final String errorCode;
  private final String message;
}
