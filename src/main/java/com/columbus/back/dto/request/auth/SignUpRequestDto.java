package com.columbus.back.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
  
  @NotBlank
  private String userId;

  @NotBlank
  @Size(min = 8)
  private String password;

  @NotBlank
  private String nickname;

  @NotBlank @Email
  private String email;

  @NotBlank @Pattern(regexp = "^[0-9]{11,13}$")
  private String telNumber;

  private String profileImage;
}
