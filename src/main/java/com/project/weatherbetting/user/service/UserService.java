package com.project.weatherbetting.user.service;

import com.project.weatherbetting.common.exception.EmailAlreadyExistsException;
import com.project.weatherbetting.common.exception.UserNotFoundException;
import com.project.weatherbetting.user.dto.UserCreateRequest;
import com.project.weatherbetting.user.entity.Role;
import com.project.weatherbetting.user.entity.User;
import com.project.weatherbetting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // TODO User CUD 구현하기
    @Transactional
    public void create(UserCreateRequest req) {

        if(userRepository.existsUserByEmail(req.email())) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다. : " + req.email());
        }

        User user = User.builder()
                .email(req.email())
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .role(Role.USER)
                .balance(new BigDecimal(100000)) // 회원가입시 지급하는 포인트
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException("해당 사용자를 찾을 수 없습니다. ID : " + id);
        }

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다. ID : " + id));
    }

    public void update() {}
    public void delete() {}

}
