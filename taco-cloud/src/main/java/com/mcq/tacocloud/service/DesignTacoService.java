package com.mcq.tacocloud.service;

import com.mcq.tacocloud.model.Ingredient;
import com.mcq.tacocloud.repo.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DesignTacoService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Ingredient> getIngredientsByType(Ingredient.Type type) {
        return getAllIngredients().stream()
                .filter(ingredient -> ingredient.getType() == type)
                .collect(Collectors.toList());
    }
}
