package com.mcq.tacocloud;

import com.mcq.tacocloud.model.Ingredient;
import com.mcq.tacocloud.repo.IngredientRepository;
import com.mcq.tacocloud.security.AppUser;
import com.mcq.tacocloud.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(
            IngredientRepository ingredientRepo,
            UserRepository userRepo,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Load ingredients
            ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            ingredientRepo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            ingredientRepo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            ingredientRepo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            ingredientRepo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

            // Load default user
            if (userRepo.findByUsername("testuser") == null) {
                AppUser user = new AppUser(
                        "testuser",
                        passwordEncoder.encode("password"),
                        "Test User",
                        "123 Main St",
                        "Anytown",
                        "State",
                        "12345",
                        "555-1234"
                );
                userRepo.save(user);
                log.info("Default user 'testuser' created.");
            } else {
                log.info("Default user 'testuser' already exists.");
            }
        };
    }

}
