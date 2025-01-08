package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://211.188.60.86:8080").build();
    }

    public void getApiData(String endpoint) {
        try {
    	webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class) // 응답을 String으로 변환
                .block(); // 동기적으로 결과를 반환
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

}
