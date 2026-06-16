package com.project.weatherbetting.common.advice;

import com.project.weatherbetting.common.ErrorResponse;
import com.project.weatherbetting.common.exception.EmailAlreadyExistsException;
import com.project.weatherbetting.common.exception.RegionNotFoundException;
import com.project.weatherbetting.common.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FileNotFoundException.class, UserNotFoundException.class, UsernameNotFoundException.class, RegionNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        String message = e.getMessage();
        return ResponseEntity.status(404).body(new ErrorResponse(message));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(Exception e) {
        String message = e.getMessage();
        return ResponseEntity.status(409).body(new ErrorResponse(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .getFirst()
                .getDefaultMessage();

        return ResponseEntity.status(400).body(new ErrorResponse(message));
    }

}
