package com.project.weatherbetting.user.controller;

import com.project.weatherbetting.user.dto.UserCreateRequest;
import com.project.weatherbetting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest req) {

        userService.create(req);

        log.info("✅ 회원가입 완료! : {}", req.email());

        return ResponseEntity.status(201).build();
    }


}

