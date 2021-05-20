package com.example.guestservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import java.time.Instant;


@Entity
@Data
@Table(name = "guest", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  private String name;
  private String email;
  private String phone;
  private Instant createdTime;
}
