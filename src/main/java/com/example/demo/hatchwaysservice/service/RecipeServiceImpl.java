package com.example.demo.hatchwaysservice.service;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.repository.RecipeFileRepositoryImpl;
import com.example.demo.hatchwaysservice.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    public List<RecipeEntity> findAll() {
        return repository.findAll();
    }

    public Optional<RecipeEntity> findByName(String recipeName) {
        return Optional.ofNullable(repository.findByName(recipeName));
    }

    public RecipeEntity save(RecipeEntity recipe) {
        return repository.save(recipe);
    }

    public void update(RecipeEntity recipe) {
        repository.save(recipe);
    }

    public void delete(RecipeEntity recipe) {
        repository.delete(recipe);
    }

}
