package com.example.guestservice.repository;

import com.example.guestservice.model.entity.GuestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Integer> {

  Optional<GuestEntity> findById(BigInteger guestId);

  Optional<GuestEntity> deleteById(BigInteger guestId);

}
