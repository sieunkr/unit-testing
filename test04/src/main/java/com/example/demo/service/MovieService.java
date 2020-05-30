package com.example.demo.service;

import com.example.demo.core.MovieGroup;
import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findByQuery(String query) {

        return getMovieGroup(query).getList();
    }

    public double calculateAverageUserRating(String query) {

        return getMovieGroup(query).calculateAverageUserRating().getAsDouble();
    }

    private MovieGroup getMovieGroup(String query) {

        List<MovieDTO> list = movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(removeSpecialCharacter(m.getTitle()))
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());

        return new MovieGroup(list);
    }

    private String removeSpecialCharacter(String str) {

        String resultStr = str;
        resultStr = StringUtils.replace(resultStr, "<b>", "");
        resultStr = StringUtils.replace(resultStr, "</b>", "");
        return resultStr;
    }
}