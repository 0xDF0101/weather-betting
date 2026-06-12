package com.project.weatherbetting.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public class ErrorResponseImpl implements ErrorResponse {

    private HttpStatus status;

    public ErrorResponseImpl(HttpStatus status) {
        this.status = status;
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

    @Override
    public ProblemDetail getBody() {
        return null;
    }


    /**
     * Question.
     * ErrorResponse를 구현해서 Advice에서 기깔나게 응답해주고 싶은데 오버라이딩이 잘 안되네?
     * 어떤 식으로 사용할 수 있는지 알려줘
     */
}
