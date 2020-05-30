package com.example.demo.core;

import com.example.demo.core.dto.MovieDTO;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<MovieDTO> list;

    public MovieGroup(List<MovieDTO> list) {
        this.list = excludeIfZeroRanking(list);
    }

    public List<MovieDTO> getList() {
        return sortByRanking();
    }

    public OptionalDouble calculateAverageUserRating() {
        return sortByRanking().stream()
                .limit(2)   //TODO:매직넘버
                .mapToDouble(MovieDTO::getUserRating)
                .average();
    }

    private List<MovieDTO> sortByRanking() {
        return list.stream()
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    //TODO: 파라미터로 넘기는 구조가 좋아보이지 않는데, 어떻게 개선하면 좋을까?
    private List<MovieDTO> excludeIfZeroRanking(List<MovieDTO> list) {
        return list.stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
                .collect(Collectors.toList());
    }
}