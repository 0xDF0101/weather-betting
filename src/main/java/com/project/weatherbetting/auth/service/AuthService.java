package com.project.weatherbetting.auth.service;

import com.project.weatherbetting.auth.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public boolean doLogin(LoginRequest req, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.password()));

            // SecurityContextHolder에 저장 ---> 이번 요청에 대해서만 유효
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticated);
            SecurityContextHolder.setContext(context);

            // SecurityContextRepository에 저장 ---> 세션 저장소에 저장 (다음 요청에도 유효함)
            securityContextRepository.saveContext(context,request, response);

            return true;
        } catch(AuthenticationException e) {
            return false;
        }
    }

}
