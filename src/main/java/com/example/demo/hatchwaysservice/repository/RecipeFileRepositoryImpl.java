package com.example.demo.hatchwaysservice.repository;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.entity.Recipes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RecipeFileRepositoryImpl { //implements RecipeRepository{

    private Map<String, RecipeEntity> repository;

    /**
     *
     * @param filename the data store file name in application properties
     */
    public RecipeFileRepositoryImpl(@Value("${data.filename}") String filename) {
        this.repository = new HashMap<>();
        String fileContents = getContentStringFromFile(filename);
        for (RecipeEntity recipe : getListFromContentString(fileContents))
            this.repository.put(recipe.getName(), recipe);

    }

    public List<RecipeEntity> findAll() {
        return new ArrayList<>(repository.values());
    }

    public RecipeEntity findByName(String name) {
        return repository.get(name);
    }

    public void save(RecipeEntity recipe) {
        repository.put(recipe.getName(), recipe);
    }

    public void delete(RecipeEntity recipe) {
        repository.remove(recipe.getName());
    }

    //TODO throws IOException?
    private String getContentStringFromFile(String filename) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private List<RecipeEntity> getListFromContentString(String fileContents) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Recipes recipes = objectMapper.readValue(fileContents, new TypeReference<>() {
            });
            return recipes.getRecipes();
        } catch(IOException err) {
            //TODO ObjectMapper handle exception
            err.printStackTrace();
        }
        return new ArrayList<>();
    }
}
