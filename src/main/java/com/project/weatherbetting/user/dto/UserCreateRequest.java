package com.project.weatherbetting.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
    @Email(message = "유효한 이메일을 입력해주세요")
    @NotBlank(message = "이메일은 공백일 수 없습니다")
    String email,
    @Size(min=1, max=20, message = "사용자 이름은 4자 이상, 20자 미만이어야 합니다")
    String username,
    @Size(min=8, max=20, message = "비밀번호는 8자 이상, 20자 미만이어야 합니다")
    String password
) {
}
