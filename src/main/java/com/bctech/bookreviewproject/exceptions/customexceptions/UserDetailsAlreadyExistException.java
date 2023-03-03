package com.bctech.bookreviewproject.exceptions.customexceptions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class UserDetailsAlreadyExistException extends RuntimeException {

    protected String message;
    protected HttpStatus status;

    public UserDetailsAlreadyExistException(String message1) {
        this.message = String.format("User with credentials %s already exist", message1);
        this.status = HttpStatus.CONFLICT;
    }


}





