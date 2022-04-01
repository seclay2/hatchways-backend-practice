package com.example.demo.hatchwaysservice.repository;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.entity.Recipes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    private final Map<String, RecipeEntity> data;

    /**
     * @param filename the data store file name in application properties
     */
    @Autowired
    public RecipeRepositoryImpl(@Value("${data.filename}") String filename) {
        this.data = new HashMap<>();
        String fileContents = getContentStringFromFile(filename);
        for (RecipeEntity recipe : getListFromContentString(fileContents)) {
            this.data.put(recipe.getName(), recipe);
        }

    }

    public List<RecipeEntity> findAll() {
        return new ArrayList<>(data.values());
    }

    public RecipeEntity findByName(String name) {
        return data.get(name);
    }

    public void save(RecipeEntity recipe) {
        data.put(recipe.getName(), recipe);
    }

    public void delete(RecipeEntity recipe) {
        data.remove(recipe.getName());
    }

    //TODO throws IOException?
    private String getContentStringFromFile(String filename) {
        InputStream inputStream = RecipeRepositoryImpl.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private List<RecipeEntity> getListFromContentString(String fileContents) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Recipes recipes = objectMapper.readValue(fileContents, Recipes.class);
            return recipes.getRecipes();
        } catch (IOException err) {
            //TODO ObjectMapper handle exception
            err.printStackTrace();
        }
        return new ArrayList<>();
    }
}
