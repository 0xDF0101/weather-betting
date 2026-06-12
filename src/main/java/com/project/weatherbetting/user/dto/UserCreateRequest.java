package com.project.weatherbetting.user.dto;

public record UserCreateRequest(
    String email,
    String username,
    String password
) {
}
