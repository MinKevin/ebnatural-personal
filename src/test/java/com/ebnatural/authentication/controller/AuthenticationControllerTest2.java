package com.ebnatural.authentication.controller;

import com.ebnatural.authentication.dto.request.LoginRequest;
import com.ebnatural.authentication.dto.request.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationControllerTest2 {
    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @Order(1)
    public void 회원가입() throws Exception {
        String url = "http://localhost:" + port + "/register";
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test@gmail.com");
        registerRequest.setPassword("1q2w3e4r!@");
        registerRequest.setPasswordConfirm("1q2w3e4r!@");
        registerRequest.setBusinessName("business_name");
        registerRequest.setRepresentative("representative");
        registerRequest.setBusinessNumber("business_number");
        registerRequest.setPostalCode("postal_code");
        registerRequest.setAddress("address");
        registerRequest.setBusinessRegistration("business_registration");
        registerRequest.setManager("manager");
        registerRequest.setManagerEmail("manager_email");
        registerRequest.setManagerPhoneNumber("manager_phone_number");
        registerRequest.setTermsOfServices(new ArrayList<>(List.of(true, true)));

        log.info("registerRequest={}", new ObjectMapper().writeValueAsString(registerRequest));

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                                .writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @Order(2)
    public void 로그인() throws Exception {
        String url = "http://localhost:" + port + "/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test@gmail.com");
        loginRequest.setPassword("1q2w3e4r!@");

        mvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
