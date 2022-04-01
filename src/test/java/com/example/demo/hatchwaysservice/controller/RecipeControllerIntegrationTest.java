package com.example.demo.hatchwaysservice.controller;

import com.example.demo.hatchwaysservice.entity.RecipeEntity;
import com.example.demo.hatchwaysservice.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(RecipeController.class)
public class RecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService service;

    @Test
    public void givenValidPost_returnStatusCreated() throws Exception {
        RecipeEntity recipe = RecipeEntity.builder()
                .name("testAddRecipe")
                .ingredients(new String[] {"testIngredient1", "testIngredient2"})
                .instructions(new String[] {"testInstruction1", "testInstruction2"})
                .build();

        String recipeJsonString = new ObjectMapper().writeValueAsString(recipe);

        this.mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(recipeJsonString))
                .andDo(print())
                .andExpect(status().isCreated());

    }
}
