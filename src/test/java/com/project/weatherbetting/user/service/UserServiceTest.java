package com.project.weatherbetting.user.service;

import com.project.weatherbetting.common.exception.EmailAlreadyExistsException;
import com.project.weatherbetting.user.dto.UserCreateRequest;
import com.project.weatherbetting.user.entity.User;
import com.project.weatherbetting.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    String email;
    UserCreateRequest req;
    @BeforeEach
    void setUp() {
        email = "email@mail.com";
        req = new UserCreateRequest(email, "username", "password123");
    }

    @Test
    @DisplayName("회원가입 - 이미 존재하는 이메일인 경우")
    void create_duplicated() {
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.create(req);
        });

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 - 정상적으로 완료")
    void create_success() {
        when(userRepository.existsUserByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(req.password())).thenReturn("hashedPassword");

        userService.create(req);

        verify(userRepository, times(1)).save(any(User.class));
    }
}