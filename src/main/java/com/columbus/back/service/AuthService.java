package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.auth.SignInRequestDto;
import com.columbus.back.dto.request.auth.SignUpRequestDto;
import com.columbus.back.dto.response.auth.SignInResponseDto;
import com.columbus.back.dto.response.auth.SignUpResponseDto;

public interface AuthService {

  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
  
}
