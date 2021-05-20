package com.example.guestservice.controller;

import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;
import com.example.guestservice.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class GuestController implements IGuestController {

  private final GuestService guestService;

  public GuestController(GuestService guestService) {
    this.guestService = guestService;
  }

  @Override
  public ResponseEntity<GuestResponse> createGuest(GuestCreatedRequest guest) {
    return ResponseEntity.ok(guestService.create(guest));
  }

  @Override
  public ResponseEntity<GuestResponse> getGuest(BigInteger guestId) {
    return ResponseEntity.ok(guestService.get(guestId));
  }

  @Override
  public ResponseEntity<GuestResponse> updateGuest(@PathVariable("guest-id") BigInteger guestId,
                                                   @RequestBody GuestUpdatedRequest guest) {
    return ResponseEntity.ok(guestService.update(guestId, guest));
  }

  @Override
  public ResponseEntity<Boolean> deleteGuest(BigInteger guestId) {
    return ResponseEntity.ok(guestService.delete(guestId));
  }
}
