package com.example.demo.service;

import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDTO> findByQuery(String query) {

        List<MovieDTO> list = movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());

        return sortByUserRating(list);
    }

    private List<MovieDTO> sortByUserRating(List<MovieDTO> list) {

        return list.stream()
                .sorted((a, b) -> (int)(b.getUserRating()*100) - (int)(a.getUserRating()*100))  //TODO: 깔끔하지 않음..
                //.sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1 : -1)
                .collect(Collectors.toList());
    }
}