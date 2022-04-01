package com.example.demo.hatchwaysservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RecipeRepository repository;

    @BeforeAll
    public void initialSeedValidation() {
        List<RecipeEntity> recipes = repository.findAll();
        assertThat(recipes).hasSize(3);
        assertThat(recipes.stream().map(RecipeEntity::getName).collect(Collectors.toList()))
            .containsExactlyInAnyOrder("scrambledEggs", "garlicPasta", "chai");
    }

    @BeforeEach
    public void setup() {
        for (RecipeEntity recipe : repository.findAll()) {
            repository.delete(recipe);
        }
        assertThat(repository.findAll()).hasSize(0);

        repository.save(RecipeEntity.builder()
            .name("my first recipe")
            .ingredients(new String[] {"ingredient 1", "ingredient 2"})
            .instructions(new String[] {"instruction 1", "instruction 2"})
            .build());

        repository.save(RecipeEntity.builder()
            .name("my second recipe")
            .ingredients(new String[] {"ingredient 1", "ingredient 2"})
            .instructions(new String[] {"instruction 1", "instruction 2"})
            .build());
    }

    @Test
    public void findAll() {
        List<RecipeEntity> recipes = repository.findAll();
        assertThat(recipes).hasSize(2);
        assertThat(recipes.stream().map(RecipeEntity::getName).collect(Collectors.toList()))
            .containsExactlyInAnyOrder("my first recipe", "my second recipe");
    }

    @Test
    public void save_and_find() {
        RecipeEntity newRecipe = RecipeEntity.builder()
            .name("my test recipe")
            .ingredients(new String[] {"ingredient 1", "ingredient 2"})
            .instructions(new String[] {"instruction 1", "instruction 2"})
            .build();

        repository.save(newRecipe);
        assertThat(repository.findAll()).hasSize(3);

        RecipeEntity foundRecipe = repository.findByName("my test recipe");

        assertThat(foundRecipe).isNotNull();
        assertThat(foundRecipe.getName()).isEqualTo(newRecipe.getName());
        assertThat(foundRecipe.getIngredients()).hasSize(2);
        assertThat(foundRecipe.getIngredients()).isEqualTo(newRecipe.getIngredients());
        assertThat(foundRecipe.getInstructions()).hasSize(2);
        assertThat(foundRecipe.getInstructions()).containsExactly(newRecipe.getInstructions());
    }

    @Test
    public void delete() {
        RecipeEntity found = repository.findByName("my first recipe");
        assertThat(found).isNotNull();

        repository.delete(found);
        RecipeEntity notFound = repository.findByName("my first recipe");
        assertThat(notFound).isNull();

        assertThat(repository.findAll()).hasSize(1);
    }
}
