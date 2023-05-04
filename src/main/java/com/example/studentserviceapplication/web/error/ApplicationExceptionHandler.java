package com.example.studentserviceapplication.web.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    Logger logger = LogManager.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> translateException(Exception exception) {
        logger.info("Action failed caused by {}", exception.getCause());
        if (exception instanceof HttpClientErrorException) {
            return ResponseEntity.status(((HttpClientErrorException) exception).getStatusCode()).body(new ExceptionResponse(
                    exception.getCause().getMessage(),
                    ((HttpClientErrorException) exception).getStatusCode().value(),
                    exception.getMessage()
            ));
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.status(((HttpRequestMethodNotSupportedException) exception).getStatusCode()).body(new ExceptionResponse(
                    exception.getCause().getMessage(),
                    ((HttpRequestMethodNotSupportedException) exception).getStatusCode().value(),
                    exception.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new ExceptionResponse(exception.getMessage()));
        }
    }
}
