package com.mcq.tacocloudclient.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@RestController
@AllArgsConstructor
public class IngredientController {

    private final RestClient restClient;

    @GetMapping("/testing")
    public String fetchIngredients() {
        return restClient.get()
                .uri("http://localhost:8081/api/ingredients")
                .attributes(clientRegistrationId("taco-client"))
                .retrieve()
                .body(String.class);
    }
}
