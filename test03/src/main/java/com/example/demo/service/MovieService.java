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

        List<MovieDTO> list = movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(removeSpecialCharacter(m.getTitle()))
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());

        return excludeIfZeroRanking(sortByUserRating(list));
    }

    private List<MovieDTO> sortByUserRating(List<MovieDTO> list) {

        return list.stream()
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }

    private List<MovieDTO> excludeIfZeroRanking(List<MovieDTO> list) {

        return list.stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
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