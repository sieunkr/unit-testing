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

    public List<MovieDTO> findByQueryOrderRating(String query) {

        return getMovieGroup(query).getListOrderRating();
    }

    public double calculateAverageUserRating(String query) {

        return getMovieGroup(query).calculateAverageUserRating().getAsDouble();
    }

    private MovieGroup getMovieGroup(String query) {

        return new MovieGroup(findByQueryImpl(query));
    }

    //TODO: 메서드 네이밍 고민 중
    private List<MovieDTO> findByQueryImpl(String query) {

        return movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getCleanTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}