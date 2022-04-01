package com.example.demo.hatchwaysservice.repository;

import com.example.demo.BaseUnitTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeFileRepositoryUnitTest extends BaseUnitTest {

    @Test
    public void findAllTest() {
        TestHarness testHarness = new TestHarness();

        List<RecipeEntity> recipeList = testHarness.repository.findAll();

        assertThat(recipeList).hasSize(3);
    }

    @Test
    public void findByNameTest() {
        TestHarness testHarness = new TestHarness();

        String testName = "scrambledEggs";
        String[] testIngredients = new String[] {
                "1 tsp oil",
                "2 eggs",
                "salt" };
        String[] testInstructions = new String[] {
                "Beat eggs with salt",
                "Heat oil in pan",
                "Add eggs to pan when hot",
                "Gather eggs into curds, remove when cooked",
                "Salt to taste and enjoy" };
        RecipeEntity recipe = testHarness.repository.findByName(testName);

        assert(recipe != null);
        assert(recipe.getName().equals(testName));
        assert(Arrays.equals(recipe.getIngredients(), testIngredients));
        assert(Arrays.equals(recipe.getInstructions(), testInstructions));
    }

    @Test
    public void saveTest() {
        TestHarness testHarness = new TestHarness();

        RecipeEntity testRecipe = RecipeEntity.builder()
                .name("testRecipe")
                .ingredients(new String[] {"testIngredient1"})
                .instructions(new String[] {"testInstruction1"})
                .build();

        testHarness.repository.save(testRecipe);

        RecipeEntity recipe = testHarness.repository.findByName("testRecipe");

        assert(recipe.equals(testRecipe));
    }

    @Test
    public void deleteTest() {
        TestHarness testHarness = new TestHarness();

        String testName = "scrambledEggs";
        RecipeEntity testRecipe = testHarness.repository.findByName(testName);
        testHarness.repository.delete(testRecipe);

        assert(testHarness.repository.findByName(testName) == null);
    }

    @Test
    public void badDataTest() {
        RecipeFileRepositoryImpl repository = new RecipeFileRepositoryImpl("badData.json");

        assert(repository.findAll().size() == 0);
    }

    private static class TestHarness {

        public final RecipeFileRepositoryImpl repository = new RecipeFileRepositoryImpl("Data.json");
    }

}
