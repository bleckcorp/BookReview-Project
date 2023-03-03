package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.dto.request.LoginDTO;
import com.bctech.bookreviewproject.dto.response.TokenDTO;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;

public interface AuthenticationService {


    TokenDTO authenticate(LoginDTO loginDTO, HttpServletRequest request) throws BadJOSEException, ParseException, JOSEException;

    String getUserNameFromToken(String token);
}
