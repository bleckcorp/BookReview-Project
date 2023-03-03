package com.bctech.bookreviewproject.exceptions.customexceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@Getter
public class BadTokenException extends RuntimeException{
    protected String message;
    protected HttpStatus status;

    public BadTokenException(String token) {
    this.message = String.format("Token %s is not valid", token);
    this.status = HttpStatus.BAD_REQUEST;
        }
}
