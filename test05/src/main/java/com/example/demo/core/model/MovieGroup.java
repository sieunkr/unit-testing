package com.example.demo.core.model;

import com.example.demo.core.model.Movie;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<Movie> list;
    private final static int AVERAGE_USER_RATING_TOP_NUM = 2;

    public MovieGroup(List<Movie> list) {
        this.list = list;
    }

    public List<Movie> getList() {
        return list;
    }

    public List<Movie> getListOrderRating() {
        return list.stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    public OptionalDouble calculateAverageUserRating() {
        return getListOrderRating().stream()
                .limit(2)
                .mapToDouble(Movie::getUserRating)
                .average();
    }
}