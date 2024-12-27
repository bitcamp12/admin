package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://back.30ticket.shop").build();
    }

    public Mono<String> getApiData(String endpoint) {
        return webClient.get()
                .uri(endpoint)
                .retrieve() // 요청을 보냄
                .bodyToMono(String.class); // 응답을 String으로 받음
    }
}
