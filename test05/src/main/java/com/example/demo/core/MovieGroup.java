package com.example.demo.core;

import com.example.demo.core.dto.MovieDTO;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<MovieDTO> list;

    public MovieGroup(List<MovieDTO> list) {
        this.list = list;
    }

    public List<MovieDTO> getList() {
        return list;
    }

    public List<MovieDTO> getListOrderRating() {
        return list.stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    public OptionalDouble calculateAverageUserRating() {
        return getListOrderRating().stream()
                .limit(2)   //TODO:매직넘버
                .mapToDouble(MovieDTO::getUserRating)
                .average();
    }
}