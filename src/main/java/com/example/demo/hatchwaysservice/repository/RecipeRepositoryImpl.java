package com.example.demo.hatchwaysservice.repository;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    private Map<String, RecipeEntity> data;

    public RecipeRepositoryImpl() {
        this.data = new HashMap<>();
    }

    public List<RecipeEntity> findAll() {
        return new ArrayList<>(data.values());
    }

    public RecipeEntity findByName(String name) {
        return data.get(name);
    }

    public RecipeEntity save(RecipeEntity recipe) {
        data.put(recipe.getName(), recipe);
        return data.get(recipe.getName());
    }

    public void delete(RecipeEntity recipe) {
        data.remove(recipe.getName());
    }

    public void deleteAll() {
        data.clear();
    }

}
