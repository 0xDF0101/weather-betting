package com.project.weatherbetting.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
