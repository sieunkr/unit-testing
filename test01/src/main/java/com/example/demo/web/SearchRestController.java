package com.example.demo.web;

import com.example.demo.core.dto.MovieDTO;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SearchRestController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<MovieDTO> searchMovies(@RequestParam(name = "q") String query) {

        return movieService.findByQuery(query);
    }
}
