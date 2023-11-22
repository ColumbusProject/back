package com.columbus.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.columbus.back.dto.request.auth.SignInRequestDto;
import com.columbus.back.dto.request.auth.SignUpRequestDto;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.auth.SignInResponseDto;
import com.columbus.back.dto.response.auth.SignUpResponseDto;
import com.columbus.back.entity.UserEntity;
import com.columbus.back.provider.JwtProvider;
import com.columbus.back.repository.UserRepository;
import com.columbus.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

    try {

        String userId = dto.getUserId();
        String nickname = dto.getNickname();
        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();

        boolean hasUserId = userRepository.existsById(userId);
        if (hasUserId) return SignUpResponseDto.duplicateUserId();

        boolean hasNickname = userRepository.existsByNickname(nickname);
        if (hasNickname) return SignUpResponseDto.duplicateNickname();

        boolean hasEmail = userRepository.existsByEmail(email);
        if (hasEmail) return SignUpResponseDto.duplicateEmail();

        boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
        if (hasTelNumber) return SignUpResponseDto.duplicateTelNumber();

        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        dto.setPassword(encodedPassword);

        UserEntity userEntity = new UserEntity(dto);
        userRepository.save(userEntity);

    } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }

    return SignUpResponseDto.success();
  }

  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

    String token = null;

    try {

        String userId = dto.getUserId();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) return SignInResponseDto.signInFailed();

        String password = dto.getPassword();
        String encodedPassword = userEntity.getPassword();

        boolean isMatched = passwordEncoder.matches(password, encodedPassword);
        if (!isMatched) return SignInResponseDto.signInFailed();

        token = jwtProvider.create(userId);

    } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }

    return SignInResponseDto.success(token);
  }
  
}
