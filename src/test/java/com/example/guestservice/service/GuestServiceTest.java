package com.example.guestservice.service;

import com.example.guestservice.exception.GuestServiceException;
import com.example.guestservice.model.entity.GuestEntity;
import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;
import com.example.guestservice.repository.GuestRepository;
import com.example.guestservice.service.implementation.GuestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

@SpringBootTest
public class GuestServiceTest {
  private static final String EMAIL = "test@gmail.com";
  private static final String NAME = "test";
  private static final String PHONE = "999";
  private static final String TIME = "2021-05-20T10:16:41.928407100Z";
  private static final String ONE = "1";
  private GuestEntity guest = GuestEntity.builder().build();
  @Mock
  GuestRepository guestRepository;
  GuestService guestService;

  @BeforeEach
  public void init() {
    guestService = new GuestServiceImpl(guestRepository);
    guest.setEmail(EMAIL);
    guest.setName(NAME);
    guest.setPhone(PHONE);
    guest.setCreatedTime(Instant.parse(TIME));
  }
  @Test
  public void createGuest_shouldWork() {
    Mockito.when(guestRepository.save(Mockito.any(GuestEntity.class))).thenReturn(guest);
    GuestResponse result  = guestService.create(GuestCreatedRequest.builder().build());
    Assertions.assertEquals(EMAIL, result.getEmail());
    Assertions.assertEquals(NAME, result.getName());
    Assertions.assertEquals(PHONE, result.getPhone());
    Assertions.assertEquals(Instant.parse(TIME), result.getCreatedTime());
  }

  @Test
  public void getGuest_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(guest));
    GuestResponse result  = guestService.get(new BigInteger(ONE));
    Assertions.assertEquals(EMAIL, result.getEmail());
    Assertions.assertEquals(NAME, result.getName());
    Assertions.assertEquals(PHONE, result.getPhone());
    Assertions.assertEquals(Instant.parse(TIME), result.getCreatedTime());
  }

  @Test
  public void getGuestNotFoundException_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.get(new BigInteger(ONE)));
  }

  @Test
  public void updateGuest_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(guest));
    Mockito.when(guestRepository.save(Mockito.any(GuestEntity.class))).thenReturn(guest);
    GuestResponse result  = guestService.update(new BigInteger("1"),
        GuestUpdatedRequest.builder().build());
    Assertions.assertEquals(EMAIL, result.getEmail());
    Assertions.assertEquals(NAME, result.getName());
    Assertions.assertEquals(PHONE, result.getPhone());
    Assertions.assertEquals(Instant.parse(TIME), result.getCreatedTime());
  }

  @Test
  public void updateGuestNotFoundException_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.update(new BigInteger(ONE)
        ,GuestUpdatedRequest.builder().build()));
  }

  @Test
  public void deleteGuest_shouldWork() {
    Mockito.when(guestRepository.deleteById(Mockito.any(BigInteger.class))).thenReturn(Optional.ofNullable(guest));
    Boolean result  = guestService.delete(new BigInteger(ONE));
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void deleteGuestNotFoundException_shouldWork() {
    Mockito.when(guestRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(GuestServiceException.class, () -> guestService.delete(new BigInteger(ONE)));
  }
}
