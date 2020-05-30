package com.example.demo.service;

import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

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

    private String removeSpecialCharacter(String str) {

        String resultStr = str;
        resultStr = StringUtils.replace(resultStr, "<b>", "");
        resultStr = StringUtils.replace(resultStr, "</b>", "");
        return resultStr;
    }
}