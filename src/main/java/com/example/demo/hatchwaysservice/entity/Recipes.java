package com.example.demo.hatchwaysservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Class to house list of recipes specifically for jackson/JSON reading data store file
 */
@Data @Builder
@Jacksonized
public class Recipes {

    private List<RecipeEntity> recipes;

}
