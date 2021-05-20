package com.example.guestservice.exception;

import lombok.Getter;

@Getter
public class GuestServiceException extends RuntimeException {

  GuestErrorResponse guestErrorResponse;

  public GuestServiceException(GuestErrorResponse guestErrorResponse) {
    this.guestErrorResponse = guestErrorResponse;
  }
}
