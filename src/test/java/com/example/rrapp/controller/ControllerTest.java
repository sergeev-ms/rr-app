package com.example.rrapp.controller;

import com.example.rrapp.domain.Greeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    final private WebTestClient webTestClient;

    @Autowired
    ControllerTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    void list() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hello")
                        .queryParam("count",1)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Greeting.class)
                .hasSize(1)
                .value(greetings -> {
                    assertThat(greetings.get(0).getMessage()).isEqualTo("Hello World");
                });
    }
}