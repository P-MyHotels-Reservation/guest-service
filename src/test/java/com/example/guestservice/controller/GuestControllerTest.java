package com.example.guestservice.controller;

import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;
import com.example.guestservice.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
public class GuestControllerTest {

  public IGuestController iGuestController;
  public GuestService guestService;

  @BeforeEach
  public void init() {
    guestService = Mockito.mock(GuestService.class);
    iGuestController = new GuestController(guestService);
  }

  @Test
  public void createGuestController_shouldWork() {
    Mockito.when(guestService.create(Mockito.any(GuestCreatedRequest.class)))
        .thenReturn(GuestResponse.builder().build());

    ResponseEntity<GuestResponse> responses = iGuestController.createGuest(Mockito.any(GuestCreatedRequest.class));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void getGuestController_shouldWork() {
    Mockito.when(guestService.get(new BigInteger("1")))
        .thenReturn(GuestResponse.builder().build());

    ResponseEntity<GuestResponse> responses = iGuestController.getGuest(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void updateGuestController_shouldWork() {
    Mockito.when(guestService.update(new BigInteger("1"), GuestUpdatedRequest.builder().build()))
        .thenReturn(GuestResponse.builder().build());

    ResponseEntity<GuestResponse> responses = iGuestController
        .updateGuest(new BigInteger("1"), GuestUpdatedRequest.builder().build());

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void deleteGuestController_shouldWork() {
    Mockito.when(guestService.delete(new BigInteger("1")))
        .thenReturn(Boolean.TRUE);

    ResponseEntity<Boolean> responses = iGuestController.deleteGuest(new BigInteger("1"));

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }
}
