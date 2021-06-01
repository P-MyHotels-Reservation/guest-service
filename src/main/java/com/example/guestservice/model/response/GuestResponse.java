package com.example.guestservice.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
public class GuestResponse {
  private BigInteger id;
  private String name;
  private String email;
  private String phone;
  private Instant createdTime;
}
