package com.bctech.bookreviewproject.controller;

import com.bctech.bookreviewproject.dto.request.LoginDTO;
import com.bctech.bookreviewproject.dto.request.SignupDTO;
import com.bctech.bookreviewproject.dto.response.APIResponse;
import com.bctech.bookreviewproject.dto.response.TokenDTO;
import com.bctech.bookreviewproject.dto.response.UserDTO;
import com.bctech.bookreviewproject.entity.Book;
import com.bctech.bookreviewproject.entity.Role;
import com.bctech.bookreviewproject.entity.User;
import com.bctech.bookreviewproject.repository.RoleRepository;
import com.bctech.bookreviewproject.repository.UserRepository;
import com.bctech.bookreviewproject.service.impl.AuthenticationImpl;
import com.bctech.bookreviewproject.service.impl.UserServiceImpl;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    /**
     * Method under test: {@link UserController#createUser(SignupDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser() {
        UserRepository userRepository = mock(UserRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationImpl authenticationService = new AuthenticationImpl(authenticationManager, userRepository,
                new UserServiceImpl(userRepository1, roleRepository, passwordEncoder, new ModelMapper()));

        UserRepository userRepository2 = mock(UserRepository.class);
        RoleRepository roleRepository1 = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationService,
                new UserServiceImpl(userRepository2, roleRepository1, passwordEncoder1, new ModelMapper()));
        userController.createUser(new SignupDTO("janedoe", "Dr Jane Doe", "jane.doe@example.org", "iloveyou"));
    }

    /**
     * Method under test: {@link UserController#createUser(SignupDTO)}
     */

    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteUser() {


        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        UserRepository userRepository = mock(UserRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationImpl authenticationService = new AuthenticationImpl(authenticationManager, userRepository,
                new UserServiceImpl(userRepository1, roleRepository, passwordEncoder, new ModelMapper()));

        UserRepository userRepository2 = mock(UserRepository.class);
        RoleRepository roleRepository1 = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationService,
                new UserServiceImpl(userRepository2, roleRepository1, passwordEncoder1, new ModelMapper()));
        userController.deleteUser(new MockHttpServletRequest());
    }

//     * Method under test: {@link UserController#deleteUser(HttpServletRequest)}

    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteUser6() {
        AuthenticationImpl authenticationImpl = mock(AuthenticationImpl.class);
        when(authenticationImpl.getUserNameFromToken((String) any())).thenReturn("janedoe");
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findByUsername((String) any())).thenReturn(Optional.empty());
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationImpl,
                new UserServiceImpl(userRepository, roleRepository, passwordEncoder, new ModelMapper()));
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn("https://example.org/example");
        userController.deleteUser(httpServletRequestWrapper);
    }

    /**
     * Method under test: {@link UserController#loginAndGetToken(LoginDTO, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoginAndGetToken() throws JOSEException, BadJOSEException, ParseException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A parent AuthenticationManager or a list of AuthenticationProviders is required
        //   See https://diff.blue/R013 to resolve this issue.

        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        UserRepository userRepository = mock(UserRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationImpl authenticationService = new AuthenticationImpl(authenticationManager, userRepository,
                new UserServiceImpl(userRepository1, roleRepository, passwordEncoder, new ModelMapper()));

        UserRepository userRepository2 = mock(UserRepository.class);
        RoleRepository roleRepository1 = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationService,
                new UserServiceImpl(userRepository2, roleRepository1, passwordEncoder1, new ModelMapper()));
        LoginDTO loginDTO = new LoginDTO("janedoe", "iloveyou");

        userController.loginAndGetToken(loginDTO, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link UserController#loginAndGetToken(LoginDTO, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoginAndGetToken2() throws JOSEException, BadJOSEException, ParseException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.bctech.bookreviewproject.exceptions.customexceptions.WrongCredentialsException: Invalid email or password
        //       at com.bctech.bookreviewproject.service.impl.AuthenticationImpl.authenticate(AuthenticationImpl.java:65)
        //       at com.bctech.bookreviewproject.controller.UserController.loginAndGetToken(UserController.java:91)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserRepository userRepository = mock(UserRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationImpl authenticationService = new AuthenticationImpl(authenticationManager, userRepository,
                new UserServiceImpl(userRepository1, roleRepository, passwordEncoder, new ModelMapper()));

        UserRepository userRepository2 = mock(UserRepository.class);
        RoleRepository roleRepository1 = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationService,
                new UserServiceImpl(userRepository2, roleRepository1, passwordEncoder1, new ModelMapper()));
        LoginDTO loginDTO = new LoginDTO("janedoe", "iloveyou");

        userController.loginAndGetToken(loginDTO, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link UserController#loginAndGetToken(LoginDTO, HttpServletRequest)}
     */
    @Test
    void testLoginAndGetToken3() throws JOSEException, BadJOSEException, ParseException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AuthenticationImpl authenticationImpl = mock(AuthenticationImpl.class);
        TokenDTO tokenDTO = new TokenDTO("ABC123", "ABC123", "ABC123", 1L);

        when(authenticationImpl.authenticate((LoginDTO) any(), (HttpServletRequest) any())).thenReturn(tokenDTO);
        UserRepository userRepository = mock(UserRepository.class);
        RoleRepository roleRepository = mock(RoleRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserController userController = new UserController(authenticationImpl,
                new UserServiceImpl(userRepository, roleRepository, passwordEncoder, new ModelMapper()));
        LoginDTO loginDTO = new LoginDTO("janedoe", "iloveyou");

        ResponseEntity<APIResponse<TokenDTO>> actualLoginAndGetTokenResult = userController.loginAndGetToken(loginDTO,
                new MockHttpServletRequest());
        assertTrue(actualLoginAndGetTokenResult.hasBody());
        assertTrue(actualLoginAndGetTokenResult.getHeaders().isEmpty());
        assertEquals(200, actualLoginAndGetTokenResult.getStatusCodeValue());
        APIResponse<TokenDTO> body = actualLoginAndGetTokenResult.getBody();
        assertEquals("SUCCESS", body.getMessage());
        assertSame(tokenDTO, body.getData());
        assertEquals(HttpStatus.ACCEPTED, body.getStatus());
        assertNull(body.getError());
        verify(authenticationImpl).authenticate((LoginDTO) any(), (HttpServletRequest) any());
    }
}

