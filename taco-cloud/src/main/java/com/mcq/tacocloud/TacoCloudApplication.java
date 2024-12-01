package com.mcq.tacocloud;

import com.mcq.tacocloud.model.Ingredient;
import com.mcq.tacocloud.model.Ingredient.Type;
import com.mcq.tacocloud.model.Taco;
import com.mcq.tacocloud.repo.IngredientRepository;
import com.mcq.tacocloud.repo.TacoRepository;
import com.mcq.tacocloud.security.AppUser;
import com.mcq.tacocloud.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

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
            PasswordEncoder passwordEncoder,
            TacoRepository tacoRepository) {

        return args -> {
            // Load ingredients
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
            Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
            ingredientRepo.save(flourTortilla);
            ingredientRepo.save(cornTortilla);
            ingredientRepo.save(groundBeef);
            ingredientRepo.save(carnitas);
            ingredientRepo.save(tomatoes);
            ingredientRepo.save(lettuce);
            ingredientRepo.save(cheddar);
            ingredientRepo.save(jack);
            ingredientRepo.save(salsa);
            ingredientRepo.save(sourCream);


            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepository.save(taco3);

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
