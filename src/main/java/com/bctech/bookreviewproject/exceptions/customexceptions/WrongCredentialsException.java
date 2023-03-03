package com.bctech.bookreviewproject.exceptions.customexceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class WrongCredentialsException extends RuntimeException{
    protected String message;
    protected HttpStatus status;

    public WrongCredentialsException(String email) {
        this.message = String.format("Credentials with email: %s not correct", email);
        this.status = HttpStatus.BAD_REQUEST;
    }
    public WrongCredentialsException() {
        this.message = "Invalid email or password";
        this.status = HttpStatus.UNAUTHORIZED;
    }
}
