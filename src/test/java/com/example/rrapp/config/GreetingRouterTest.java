package com.example.rrapp.config;

import com.example.rrapp.domain.Greeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static javax.accessibility.AccessibleRole.LIST;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingRouterTest {

    final private WebTestClient webTestClient;

    @Autowired
    GreetingRouterTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    public void route() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/controller")
                        .queryParam("count",1)
                        .queryParam("start", 1)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Greeting.class)
                .hasSize(1)
                .value(greetings -> {
                    assertThat(greetings.get(0).getMessage()).isEqualTo("Hello user");
                });
    }
}