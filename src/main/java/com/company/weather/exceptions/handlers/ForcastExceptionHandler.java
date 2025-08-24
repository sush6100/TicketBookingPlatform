package com.company.weather.exceptions.handlers;

import com.company.weather.exceptions.ReportFetchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ForcastExceptionHandler {
    @ExceptionHandler(value = {ReportFetchException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        final String bodyOfResponse = "There is an issue while fetching weather report";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(RuntimeException ex, String bodyOfResponse
            , HttpHeaders httpHeaders, HttpStatus conflict, WebRequest request) {
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }
}
