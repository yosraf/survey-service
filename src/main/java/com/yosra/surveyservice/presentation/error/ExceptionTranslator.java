package com.yosra.surveyservice.presentation.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionTranslator {
    private static final String TECHNICAL_ERROR_MSG="An error occured";
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            errors.add(error.getDefaultMessage());
                        });
        return buildErrorResponse(
               errors, TECHNICAL_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.hasLength(ex.getMessage())){
            errors.add(ex.getMessage());
        }
        return buildErrorResponse(
                errors, TECHNICAL_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ApiError> buildErrorResponse(
        List<String> errors,    String message, HttpStatus httpStatus) {
        ApiError apiError = new ApiError(httpStatus.value(), errors,message, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpHeaders.EMPTY, httpStatus);
    }

}
