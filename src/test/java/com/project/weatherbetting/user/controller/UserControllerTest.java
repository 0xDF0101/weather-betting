package com.project.weatherbetting.user.controller;

import com.project.weatherbetting.user.dto.UserCreateRequest;
import com.project.weatherbetting.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@WithMockUser
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("invalidRequests")
    @DisplayName("회원가입 - 검증 실패 케이스들")
    void createUser_invalid(UserCreateRequest req) throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    static Stream<UserCreateRequest> invalidRequests() {
        return Stream.of(
                new UserCreateRequest("not-an-email", "닉네임", "password123"),  // 이메일 형식 오류
                new UserCreateRequest("test@email.com", "닉네임", "short"),       // 비밀번호 너무 짧음
                new UserCreateRequest("", "닉네임", "password123")               // 이메일 공백
        );
    }
}