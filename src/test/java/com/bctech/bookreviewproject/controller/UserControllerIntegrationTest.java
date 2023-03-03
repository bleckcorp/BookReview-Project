package com.bctech.bookreviewproject.controller;

import com.bctech.bookreviewproject.dto.request.SignupDTO;
import com.bctech.bookreviewproject.service.RoleService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Autowired
    private RoleService roleService;


    @BeforeEach
    void setUp() {
//        Role role = new Role(null, RoleType.ROLE_USER.toString());
//            roleService.save(role);
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    }

    @Test
    void createUser() throws Exception {
        SignupDTO userRequest = new SignupDTO();
        userRequest.setFullName("Blessing Chuks");
        userRequest.setEmail("deh756jhg2@gmail.com");
        userRequest.setPassword("12345");
        userRequest.setUsername("user8679");

        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.email").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.data.fullName").value(userRequest.getFullName()))
                .andExpect(jsonPath("$.data.email").value(userRequest.getEmail()))
                .andExpect(jsonPath("$.data.username").value(userRequest.getUsername()));

    }

    @Test
    @Disabled
    void loginAndGetToken() {
    }
}