package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.response.user.GetSignInUserResponseDto;

public interface UserService {

  ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);
  
}
