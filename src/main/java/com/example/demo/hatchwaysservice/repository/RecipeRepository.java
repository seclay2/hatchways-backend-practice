package com.example.demo.hatchwaysservice.repository;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;

import java.util.List;

public interface RecipeRepository {

    List<RecipeEntity> findAll();

    RecipeEntity findByName(String name);

    void save(RecipeEntity recipe);

    void delete(RecipeEntity recipe);

}
