package com.example.guestservice.service;

import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;

import java.math.BigInteger;

public interface GuestService {

  /**
   * This method is used to create guest in database.
   *
   * @param guestRequest guest request {@link GuestCreatedRequest}
   * @return GuestResponse use {@link GuestResponse}
   */
  GuestResponse create(GuestCreatedRequest guestRequest);

  /**
   * This method is used to get user information data.
   *
   * @param guestId guest id
   * @return GuestResponse use {@link GuestResponse}
   */
  GuestResponse get(BigInteger guestId);

  /**
   * This method is used to update guest information data in database.
   *
   * @param guestId guest id
   * @param guest guest update request {@link GuestUpdatedRequest}
   * @return GuestResponse use {@link GuestResponse}
   */
  GuestResponse update(BigInteger guestId, GuestUpdatedRequest guest);

  /**
   * This method is used to delete guest by guest id.
   *
   * @param guestId
   * @return Boolean delete success return true
   *                           fail return false
   */
  Boolean delete(BigInteger guestId);

}
