package com.columbus.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.columbus.back.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

  boolean existsByUserId(String userId);
  boolean existsByNickname(String nickname);
  boolean existsByEmail(String email);
  boolean existsByTelNumber(String telNumber);

  UserEntity findByUserId(String userId);
}
