package com.example.demo.hatchwaysservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.BaseUnitTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class RecipeRepositoryUnitTest extends BaseUnitTest {

    @Test
    public void seed_and_findAll() {
        TestHarness testHarness = new TestHarness();
        List<RecipeEntity> recipes = testHarness.repository.findAll();
        assertThat(recipes).hasSize(3);
        assertThat(recipes.stream().map(RecipeEntity::getName).collect(Collectors.toList()))
            .containsExactlyInAnyOrder("scrambledEggs", "garlicPasta", "chai");
    }

    @Test
    public void save_and_findByName() {
        TestHarness testHarness = new TestHarness();

        RecipeEntity newRecipe = RecipeEntity.builder()
            .name("my test recipe")
            .ingredients(new String[] {"ingredient 1", "ingredient 2"})
            .instructions(new String[] {"instruction 1", "instruction 2"})
            .build();

        testHarness.repository.save(newRecipe);

        RecipeEntity foundRecipe = testHarness.repository.findByName("my test recipe");

        assertThat(foundRecipe).isNotNull();
        assertThat(foundRecipe.getName()).isEqualTo(newRecipe.getName());
        assertThat(foundRecipe.getIngredients()).hasSize(2);
        assertThat(foundRecipe.getIngredients()).isEqualTo(newRecipe.getIngredients());
        assertThat(foundRecipe.getInstructions()).hasSize(2);
        assertThat(foundRecipe.getInstructions()).containsExactly(newRecipe.getInstructions());
    }

    @Test
    public void delete() {
        TestHarness testHarness = new TestHarness();

        RecipeEntity garlicPasta = testHarness.repository.findByName("garlicPasta");
        assertThat(garlicPasta).isNotNull();

        testHarness.repository.delete(garlicPasta);
        RecipeEntity deletedGarlicPasta = testHarness.repository.findByName("garlicPasta");
        assertThat(deletedGarlicPasta).isNull();
    }

    @Test
    public void loadingBadData() {
        RecipeRepository repository = new RecipeRepositoryImpl("badData.json");
        assertThat(repository.findAll()).isEmpty();
    }

    private static class TestHarness {

        private final RecipeRepository repository = new RecipeRepositoryImpl("data.json");
    }
}
