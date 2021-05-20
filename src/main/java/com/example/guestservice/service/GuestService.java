package com.example.guestservice.service;

import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;

import java.math.BigInteger;

public interface GuestService {

  GuestResponse create(GuestCreatedRequest guestRequest);

  GuestResponse get(BigInteger guestId);

  GuestResponse update(BigInteger guestId, GuestUpdatedRequest guest);

  Boolean delete(BigInteger guestId);

}
