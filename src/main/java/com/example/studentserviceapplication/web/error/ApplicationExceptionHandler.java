package com.example.studentserviceapplication.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail translateException(Exception exception) {
        ProblemDetail problemDetail = null;
        if (exception instanceof HttpClientErrorException) {
            problemDetail = ProblemDetail.forStatusAndDetail(((HttpClientErrorException) exception).getStatusCode(), exception.getMessage());
        } else {
            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Action failed");
        }
        return problemDetail;
    }
}
