package com.example.guestservice.controller;

import com.example.guestservice.model.entity.GuestEntity;
import com.example.guestservice.model.request.GuestCreatedRequest;
import com.example.guestservice.model.request.GuestUpdatedRequest;
import com.example.guestservice.model.response.GuestResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/guest")
public interface IGuestController {

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "created user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestEntity.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid field",
          content = @Content),
      @ApiResponse(responseCode = "403", description = "conflict",
          content = @Content)})
  ResponseEntity<GuestResponse> createGuest(@RequestBody GuestCreatedRequest guest);

  @GetMapping("/{guest-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Get user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<GuestResponse> getGuest(@PathVariable("guest-id") BigInteger guestId);

  @PutMapping("/{guest-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "updated user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<GuestResponse> updateGuest(@PathVariable("guest-id") BigInteger guestId,
                                            @RequestBody GuestUpdatedRequest guest);

  @DeleteMapping("/{guest-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "delete user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = GuestEntity.class))}),
      @ApiResponse(responseCode = "404", description = "Not found",
          content = @Content)})
  ResponseEntity<Boolean> deleteGuest(@PathVariable("guest-id") BigInteger guestId);


}
