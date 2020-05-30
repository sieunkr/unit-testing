package com.example.demo.service;

import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findByQuery(String query) {

        return findByQueryImpl(query);
    }

    public List<MovieDTO>  findByQueryOrderRating(String query) {

        return findByQueryImpl(query).stream()
                //.sorted((a, b) -> (int)(b.getUserRating()*100) - (int)(a.getUserRating()*100))
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    //TODO: 메서드 네이밍 고민 중
    private List<MovieDTO> findByQueryImpl(String query) {

        return movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}