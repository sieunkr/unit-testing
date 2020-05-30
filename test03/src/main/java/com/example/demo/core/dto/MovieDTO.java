package com.example.demo.core.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieDTO {

    private String title;
    private String link;
    private float userRating;
}