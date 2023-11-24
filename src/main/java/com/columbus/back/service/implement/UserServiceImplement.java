package com.columbus.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.user.GetSignInUserResponseDto;
import com.columbus.back.entity.UserEntity;
import com.columbus.back.repository.UserRepository;
import com.columbus.back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
    
    UserEntity userEntity = null;

    try {

        userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) return GetSignInUserResponseDto.notExistUser();

    } catch(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }

    return GetSignInUserResponseDto.success(userEntity);

  }
  
}
