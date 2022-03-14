package com.example.demo.hatchwaysservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data @Builder
@Jacksonized
public class RecipeEntity {

    private String name;

    private String[] ingredients;

    private String[] instructions;

}
