package com.example.demo.hatchwaysservice.service;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RecipeService service;

    @Test
    public void saveRecipeTest() {
        RecipeEntity recipe = RecipeEntity.builder()
                .name("testAddRecipe")
                .ingredients(new String[] {"testIngredient1", "testIngredient2"})
                .instructions(new String[] {"testInstruction1", "testInstruction2"})
                .build();

        RecipeEntity savedRecipe = this.service.save(recipe);
        assertThat(savedRecipe).isNotNull();
        assertThat(savedRecipe).isEqualTo(recipe);
    }

}
