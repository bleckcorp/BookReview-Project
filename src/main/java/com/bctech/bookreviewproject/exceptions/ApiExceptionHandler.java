package com.bctech.bookreviewproject.exceptions;


import com.bctech.bookreviewproject.dto.response.APIResponse;
import com.bctech.bookreviewproject.exceptions.customexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<APIResponse<ErrorDetails>> handleCustomException(CustomException ex, WebRequest request) {
        int statusCode = ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(statusCode)
                .details(request.getDescription(true))
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(APIResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(HttpStatus.valueOf(statusCode))
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(value = {ApiGenericException.class})
    public ResponseEntity<Object> handleApiException(ApiGenericException exception) {
        return ResponseEntity.status(400).body(exception);
    }

    @ExceptionHandler(UserDetailsAlreadyExistException.class)
    public final ResponseEntity<APIResponse<ErrorDetails>> handleAdminNotFoundException(UserDetailsAlreadyExistException ex, WebRequest request) {
        int statusCode = ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(statusCode)
                .details(request.getDescription(true))
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(APIResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(HttpStatus.valueOf(statusCode))
                .error(errorDetails)
                .build());
    }

    //TODO: rename exceptionhandlers methods
    @ExceptionHandler(WrongCredentialsException.class)
    public final ResponseEntity<APIResponse<ErrorDetails>> handleCustomException(WrongCredentialsException ex, WebRequest request) {
        int statusCode = ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(statusCode)
                .details(request.getDescription(true))
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(APIResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(HttpStatus.valueOf(statusCode))
                .error(errorDetails)
                .build());
    }
    @ExceptionHandler(BadTokenException.class)
    public final ResponseEntity<APIResponse<ErrorDetails>> handleCustomException(BadTokenException ex, WebRequest request) {
        int statusCode = ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(statusCode)
                .details(request.getDescription(true))
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(APIResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(HttpStatus.valueOf(statusCode))
                .error(errorDetails)
                .build());
    }



}



