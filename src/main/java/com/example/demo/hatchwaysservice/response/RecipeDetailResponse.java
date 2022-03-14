package com.example.demo.hatchwaysservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeDetailResponse {

    @JsonInclude(Include.NON_NULL)
    Details details;

    @Data
    @Builder
    public static class Details {

        List<String> ingredients;

        int numSteps;
    }
}
