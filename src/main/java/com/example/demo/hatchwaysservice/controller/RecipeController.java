package com.example.demo.hatchwaysservice.controller;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.response.ErrorResponse;
import com.example.demo.hatchwaysservice.response.RecipeDetailResponse;
import com.example.demo.hatchwaysservice.response.RecipeDetailResponse.Details;
import com.example.demo.hatchwaysservice.response.RecipeNamesResponse;
import com.example.demo.hatchwaysservice.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping("/all")
    public List<RecipeEntity> getAllRecipes() {
        return recipeService.findAll();
    }

    @GetMapping
    public ResponseEntity<RecipeNamesResponse> getRecipeNames() {
        return ResponseEntity.ok(RecipeNamesResponse.builder()
                .recipeNames(recipeService.findAll().stream()
                        .map(RecipeEntity::getName)
                        .collect(Collectors.toList()))
                .build());
   }

    @GetMapping("/{recipeName}")
    public ResponseEntity<RecipeDetailResponse> getRecipeDetails(@PathVariable String recipeName) {
        return recipeService.findByName(recipeName)
                .map(entity -> ResponseEntity.ok(fromEntity(entity)))
                .orElseGet(() -> ResponseEntity.ok(RecipeDetailResponse.builder().build()));
    }

    @PostMapping
    public ResponseEntity<ErrorResponse> addRecipe(@RequestBody RecipeEntity recipe) throws URISyntaxException {
        if (recipeService.findByName(recipe.getName()).isPresent()) {
            return ResponseEntity.badRequest().body(ErrorResponse.builder().error("Recipe already exists").build());
        }
        else {
            recipeService.update(recipe);
            return ResponseEntity.created(URI.create("")).build();
        }
    }

    @PutMapping
    public ResponseEntity<ErrorResponse> updateRecipe(@RequestBody RecipeEntity recipe) {
        if (recipeService.findByName(recipe.getName()).isPresent()) {
            recipeService.update(recipe);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().error("Recipe does not exist").build());
    }

    @DeleteMapping
    public ResponseEntity<ErrorResponse> deleteRecipe(@RequestBody RecipeEntity recipe) {
        if (recipeService.findByName(recipe.getName()).isPresent()) {
            recipeService.delete(recipe);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().error("Recipe does not exist").build());
    }

    /**
     * Translate from a RecipeEntity to a RecipeDetailResponse
     *
     * @param entity the entity to translate from
     * @return the translated response
     */
    private RecipeDetailResponse fromEntity(RecipeEntity entity) {
        return RecipeDetailResponse.builder()
                .details(Details.builder()
                        .ingredients(Arrays.stream(entity.getIngredients()).collect(Collectors.toList()))
                        .numSteps(entity.getInstructions().length)
                        .build())
                .build();
    }
}
