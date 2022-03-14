package com.example.demo.hatchwaysservice.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String error;

}
