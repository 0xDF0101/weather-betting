package com.project.weatherbetting.common.advice;

import com.project.weatherbetting.common.exception.EmailAlreadyExistsException;
import com.project.weatherbetting.common.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FileNotFoundException.class, UserNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        // TODO 적절한 응답 DTO 작성 or ErrorResponse 잘 작성하기
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(Exception e) {
        // TODO 적절한 응답 DTO 작성 or ErrorResponse 잘 작성하기
        return ResponseEntity.status(409).build();
    }

}
