package com.bctech.bookreviewproject.controller;

import com.bctech.bookreviewproject.dto.request.LoginDTO;
import com.bctech.bookreviewproject.dto.request.SignupDTO;
import com.bctech.bookreviewproject.dto.response.APIResponse;
import com.bctech.bookreviewproject.dto.response.TokenDTO;
import com.bctech.bookreviewproject.dto.response.UserDTO;
import com.bctech.bookreviewproject.service.AuthenticationService;
import com.bctech.bookreviewproject.service.UserService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;


    @PostMapping("/signup")
    @Operation(summary = "signup",
            security = {@SecurityRequirement(name = "bearer-token")},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)))})
    public ResponseEntity<APIResponse<UserDTO>> createUser(@RequestBody @Valid SignupDTO request) {
        UserDTO response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<UserDTO>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(@RequestBody SignupDTO updateRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = authenticationService.getUserNameFromToken(token);

        UserDTO response = userService.updateUserByUsername(username,updateRequest);
        return ResponseEntity.ok().body(
                APIResponse.<UserDTO>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<APIResponse<Boolean>> deleteUser(HttpServletRequest request) {


        String token = request.getHeader("Authorization").replace("Bearer ", "");
           String username = authenticationService.getUserNameFromToken(token);

        Boolean response = userService.deleteUserByUsername(username);

        return ResponseEntity.ok().body(
                APIResponse.<Boolean>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<TokenDTO>> loginAndGetToken(@RequestBody @Valid LoginDTO loginDTO,
                                                                  HttpServletRequest request) throws BadJOSEException, ParseException, JOSEException {

        // we will need to authenticate the user without a token and then generate a token
        TokenDTO response = authenticationService.authenticate(loginDTO,request);

        return ResponseEntity.ok().body(
                APIResponse.<TokenDTO>builder()
                        .status(HttpStatus.ACCEPTED)
                        .message("SUCCESS")
                        .data(response)
                        .build()
        );
    }

}
