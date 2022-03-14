package com.example.demo.hatchwaysservice.service;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.repository.RecipeRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepositoryImpl recipeRepository;

    public List<RecipeEntity> findAll() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeEntity> findByName(String recipeName) {
        return Optional.ofNullable(recipeRepository.findByName(recipeName));
    }

    public void save(RecipeEntity recipe) {
        recipeRepository.save(recipe);
    }

    public void update(RecipeEntity recipe) {
        recipeRepository.save(recipe);
    }

    public void delete(RecipeEntity recipe) {
        recipeRepository.delete(recipe);
    }

}
