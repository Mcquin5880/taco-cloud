package com.mcq.tacocloudclient.controller;

import com.mcq.tacocloudclient.model.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@RestController
@AllArgsConstructor
public class IngredientController {

    private final RestClient restClient;

    @GetMapping
    public Iterable<Ingredient> fetchIngredients() {
        return restClient.get()
                .uri("http://localhost:8081/api/ingredients")
                .attributes(clientRegistrationId("taco-client"))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @PostMapping
    public ResponseEntity<Void> addIngredient(@RequestBody Ingredient ingredient) {
        return restClient.post()
                .uri("http://localhost:8081/api/ingredients")
                .attributes(clientRegistrationId("taco-client"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(ingredient)
                .retrieve()
                .toBodilessEntity();
    }
}
