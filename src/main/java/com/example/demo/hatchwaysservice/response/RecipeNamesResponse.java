package com.example.demo.hatchwaysservice.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeNamesResponse {

    private List<String> recipeNames;

}
