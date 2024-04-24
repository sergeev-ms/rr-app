package com.example.rrapp.handler;

import com.example.rrapp.domain.Greeting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        final String start = request.queryParam("start")
                .orElse("0");
        final String count = request.queryParam("count")
                .orElse("10");
        final Flux<Greeting> greetingFlux = Flux.just(
                        "Hello Word",
                        "Hello user",
                        "Hello everyone",
                        "And you hello too")
                .skip(Long.parseLong(start))
                .take(Long.parseLong(count))
                .map(Greeting::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(greetingFlux, Greeting.class);
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        final String userName = request.queryParam("user")
                .orElse("Unnamed");
        return ServerResponse.ok()
                .render("index", Map.of("user", userName));
    }
}
