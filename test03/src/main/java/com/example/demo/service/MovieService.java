package com.example.demo.service;

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

        return findByQueryImpl(query);
    }

    public List<MovieDTO> findByQueryOrderRating(String query) {

        return findByQueryImpl(query).stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    //TODO: 메서드 네이밍 고민 중
    private List<MovieDTO> findByQueryImpl(String query) {

        return movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(removeSpecialCharacter(m.getTitle()))
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }

    //TODO: 코드 좀 더 깔끔하게 변경 가능한지?
    private String removeSpecialCharacter(String str) {

        String resultStr = str;
        resultStr = StringUtils.replace(resultStr, "<b>", "");
        resultStr = StringUtils.replace(resultStr, "</b>", "");
        return resultStr;
    }
}