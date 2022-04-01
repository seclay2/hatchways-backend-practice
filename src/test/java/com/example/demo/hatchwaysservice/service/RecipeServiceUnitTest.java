package com.example.demo.hatchwaysservice.service;

import com.example.demo.BaseUnitTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.repository.RecipeRepository;
import com.example.demo.hatchwaysservice.repository.RecipeRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeServiceUnitTest extends BaseUnitTest {

    @Test
    public void saveRecipeTest() {
        TestHarness testHarness = new TestHarness();

        RecipeEntity recipe = RecipeEntity.builder()
                .name("testAddRecipe")
                .ingredients(new String[] {"testIngredient1", "testIngredient2"})
                .instructions(new String[] {"testInstruction1", "testInstruction2"})
                .build();

        RecipeEntity savedRecipe = testHarness.service.save(recipe);
        assertThat(savedRecipe).isNotNull();
        assertThat(savedRecipe).isEqualTo(recipe);

    }

    private static class TestHarness {
        private final RecipeRepository repository = new RecipeRepositoryImpl();
        public final RecipeService service = new RecipeServiceImpl(repository);
    }
}
