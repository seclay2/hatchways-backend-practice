package com.example.demo.hatchwaysservice.repository;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    RecipeRepository recipeRepositoryImpl;

    @BeforeEach
    private void initialSeed() {
        this.recipeRepositoryImpl.deleteAll();
        this.recipeRepositoryImpl.save(RecipeEntity.builder()
                .name("testRecipe1")
                .ingredients(new String[] { "testIngredient1", "testIngredient2" })
                .instructions(new String[] { "testInstruction1", "testInstruction2" })
                .build());
        this.recipeRepositoryImpl.save(RecipeEntity.builder()
                .name("testRecipe2")
                .ingredients(new String[] { "testIngredient1", "testIngredient2" })
                .instructions(new String[] { "testInstruction1", "testInstruction2" })
                .build());
    }

    @Test
    public void findAllTest() {
        List<RecipeEntity> recipeList = this.recipeRepositoryImpl.findAll();

        assertThat(recipeList).isNotNull();
        assertThat(recipeList.stream().map(RecipeEntity::getName))
                .containsExactlyInAnyOrder("testRecipe1", "testRecipe2");
    }

    @Test
    public void findByNameTest() {
        RecipeEntity recipe = this.recipeRepositoryImpl.findByName("testRecipe1");

        assertThat(recipe).isNotNull();
        assertThat(recipe.getName()).isEqualTo("testRecipe1");
        assertThat(recipe.getIngredients()).containsExactlyInAnyOrder("testIngredient1", "testIngredient2");
        assertThat(recipe.getInstructions()).containsExactlyInAnyOrder("testInstruction1", "testInstruction2");
    }

    @Test
    public void saveTest() {
        RecipeEntity recipe = RecipeEntity.builder()
                .name("testRecipe")
                .ingredients(new String[] {"testIngredient1", "testIngredient2"})
                .instructions(new String[] {"testInstruction1", "testInstruction2"})
                .build();

        this.recipeRepositoryImpl.save(recipe);

        assertThat(this.recipeRepositoryImpl.findByName("testRecipe")).isNotNull();
    }

    @Test
    public void deleteTest() {
        RecipeEntity recipe = this.recipeRepositoryImpl.findByName("scrambledEggs");

        this.recipeRepositoryImpl.delete(recipe);

        assertThat(this.recipeRepositoryImpl.findByName("scrambledEggs")).isNull();
    }



}
