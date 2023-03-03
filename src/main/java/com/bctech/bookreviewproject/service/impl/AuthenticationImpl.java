package com.bctech.bookreviewproject.service.impl;

import com.bctech.bookreviewproject.dto.request.LoginDTO;
import com.bctech.bookreviewproject.dto.response.TokenDTO;
import com.bctech.bookreviewproject.exceptions.customexceptions.WrongCredentialsException;
import com.bctech.bookreviewproject.repository.UserRepository;
import com.bctech.bookreviewproject.service.AuthenticationService;
import com.bctech.bookreviewproject.service.UserService;
import com.bctech.bookreviewproject.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;


    @Override
   public TokenDTO authenticate(LoginDTO loginDTO, HttpServletRequest request) throws BadJOSEException, ParseException, JOSEException {

       TokenDTO tokenDTO = new TokenDTO();

       try {
           Authentication authentication = authenticationManager
                   .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

           SecurityContextHolder.getContext().setAuthentication(authentication);

           if (authentication.isAuthenticated()) {

               List<String> roles = authentication.getAuthorities().stream()
                       .map(GrantedAuthority::getAuthority)
                       .collect(Collectors.toList());

               String accessToken = JwtUtil.createAccessToken(authentication.getName(),
                       String.valueOf(request.getRequestURL()),
                       roles);
               String refreshToken = JwtUtil.createRefreshToken(authentication.getName());
               long expirationTime = JwtUtil.getExpirationTime(accessToken).getTime() / 1000;

               tokenDTO.setAccess_token(accessToken);
               tokenDTO.setRefresh_token(refreshToken);
               tokenDTO.setExpires_in(expirationTime);
               tokenDTO.setToken_type("Bearer");
           }
       }
       catch (AuthenticationException e) {
           throw new WrongCredentialsException();
       }

       return tokenDTO;
   }

    @Override
    public String getUserNameFromToken(String token) {

        try {
            return JwtUtil.getSubjectFromToken(token);
        } catch (JOSEException | BadJOSEException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
}


