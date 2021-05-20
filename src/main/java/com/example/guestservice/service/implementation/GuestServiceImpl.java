package com.example.guestservice.service.implementation;

import com.example.guestservice.exception.GuestErrorResponse;
import com.example.guestservice.exception.GuestServiceException;
import com.example.guestservice.model.entity.GuestEntity;
import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;
import com.example.guestservice.repository.GuestRepository;
import com.example.guestservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

  @Autowired
  private final GuestRepository guestRepository;

  public GuestServiceImpl(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @Override
  public GuestResponse create(GuestCreatedRequest guestCreatedRequest) {
    var guest = buildGuestEntity(guestCreatedRequest);
    return convertEntityToResponse(guestRepository.save(guest));
  }

  @Override
  public GuestResponse get(BigInteger guestId) {
    return guestRepository.findById(guestId).map(this::convertEntityToResponse)
        .orElseThrow(() -> new GuestServiceException(GuestErrorResponse.GUEST_IS_NOT_FOUND));
  }

  @Override
  public GuestResponse update(BigInteger guestId, GuestUpdatedRequest guestUpdatedRequest) {
    var updatedGuestEntity = guestRepository.findById(guestId).orElseThrow(
        () -> new GuestServiceException(GuestErrorResponse.GUEST_IS_NOT_FOUND));
    var guest = buildUpdatedGuestEntity(updatedGuestEntity, guestUpdatedRequest);
    return convertEntityToResponse(guestRepository.save(guest));
  }

  @Override
  public Boolean delete(BigInteger guestId) {
    return guestRepository.deleteById(guestId).map(t-> Boolean.TRUE)
        .orElseThrow(() -> new GuestServiceException(GuestErrorResponse.GUEST_IS_NOT_FOUND));
  }

  private GuestEntity buildUpdatedGuestEntity(GuestEntity guestEntity, GuestUpdatedRequest guestUpdatedRequest) {
    guestEntity.setPhone(Optional.ofNullable(guestUpdatedRequest.getPhone()).orElse(guestEntity.getPhone()));
    guestEntity.setName(Optional.ofNullable(guestUpdatedRequest.getName()).orElse(guestEntity.getName()));
    return guestEntity;
  }

  private GuestEntity buildGuestEntity(GuestCreatedRequest guestRequest) {
    return GuestEntity.builder()
        .email(guestRequest.getEmail())
        .createdTime(Instant.now())
        .phone(guestRequest.getPhone())
        .name(guestRequest.getName()).build();
  }

  private GuestResponse convertEntityToResponse(GuestEntity save) {
    return GuestResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .email(save.getEmail())
        .phone(save.getPhone())
        .createdTime(save.getCreatedTime()).build();
  }
}
