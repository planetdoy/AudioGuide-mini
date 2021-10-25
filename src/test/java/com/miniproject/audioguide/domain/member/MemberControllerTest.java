package com.miniproject.audioguide.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void login() throws Exception {

        String loginId = "admin";
        String password = "1234";

        LoginForm loginForm = new LoginForm(loginId, password);
        String url = "http://localhost:" + port + "/api/members/login";

        ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity(url, loginForm, Object.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}



