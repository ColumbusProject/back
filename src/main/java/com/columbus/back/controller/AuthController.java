package com.columbus.back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columbus.back.dto.request.auth.SignInRequestDto;
import com.columbus.back.dto.request.auth.SignUpRequestDto;
import com.columbus.back.dto.response.auth.SignInResponseDto;
import com.columbus.back.dto.response.auth.SignUpResponseDto;
import com.columbus.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/columbus/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
    @RequestBody @Valid SignUpRequestDto requestBody
  ) {
    ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
    return response;
  }
  
  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
    @RequestBody @Valid SignInRequestDto requestBody
  ) {
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
    return response;
  }
}
