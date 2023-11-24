package com.columbus.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.columbus.back.dto.request.auth.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
  @Id
  private String userId;
  private String password;
  private String nickname;
  private String email;
  private String telNumber;
  private String profileText;
  private String profileImage;
  
  public UserEntity(SignUpRequestDto dto) {
    this.userId = dto.getUserId();
    this.password = dto.getPassword();
    this.nickname = dto.getNickname();
    this.email = dto.getEmail();
    this.telNumber = dto.getTelNumber();
  }
}
