package com.awesomeforum.aaf.service;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class WhoaService {

    private final WebClient webClient;

    public WhoaService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://whoa.onrender.com")
                .build();
    }

    public String createRandomWhoa() {
        List<Map<String, Object>> responseList = webClient.get()
                .uri("/whoas/random")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .block();

        if (responseList != null && !responseList.isEmpty()) {
            Object whoaLine = responseList.get(0).get("full_line");
            return whoaLine != null ? whoaLine.toString() : null;
        }
        return null;
    }

}