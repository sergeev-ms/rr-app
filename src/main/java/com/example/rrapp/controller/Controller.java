package com.example.rrapp.controller;

import com.example.rrapp.domain.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class Controller {

    @GetMapping
    public Flux<Greeting> list(@RequestParam(defaultValue = "0") String start,
                               @RequestParam(defaultValue = "10") String count) {
        return Flux.just(
                        "Hello Word",
                        "Hello user",
                        "Hello everyone",
                        "And you hello too")
                .skip(Long.parseLong(start))
                .take(Long.parseLong(count))
                .map(Greeting::new);
    }
}
