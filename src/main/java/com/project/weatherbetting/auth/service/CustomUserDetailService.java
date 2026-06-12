package com.project.weatherbetting.auth.service;

import com.project.weatherbetting.auth.CustomUserDetails;
import com.project.weatherbetting.common.exception.UserNotFoundException;
import com.project.weatherbetting.user.entity.User;
import com.project.weatherbetting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found ID : " + username));

        return new CustomUserDetails(user);
    }
}
