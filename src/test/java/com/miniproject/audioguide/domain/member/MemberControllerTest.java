package com.miniproject.audioguide.domain.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void login() throws Exception {

        String loginId = "admin";
        String password = "1234";

        Map content = new HashMap<>();
        content.put("loginId", loginId);
        content.put("password", password);

        String jsonContent = new ObjectMapper().writeValueAsString(content);

        mvc.perform(
                post("/api/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginId").value(loginId));
    }
}



