package com.example.demo.hatchwaysservice.service;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    /**
     * Retrieves a List of all recipes from data store
     *
     * @return the list of recipes
     */
    List<RecipeEntity> findAll();

    /**
     * Retrieves a single recipe based on name
     *
     * @param name the name of the recipe
     * @return the recipe as an Optional
     */
    Optional<RecipeEntity> findByName(String name);

    /**
     * Adds a new recipe to data store
     *
     * @param recipe the recipe to be added
     */
    RecipeEntity save(RecipeEntity recipe);

    /**
     * Update existing recipe in the data store
     *
     * @param recipe the recipe to be updated
     */
    void update(RecipeEntity recipe);

    /**
     * Deletes a single recipe from the data store
     *
     * @param recipe the recipe to be deleted
     */
    void delete(RecipeEntity recipe);

}
