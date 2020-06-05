package com.example.demo.web;

import com.example.demo.core.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchRestController {

    private final MovieService movieService;

    public SearchRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> searchMovies(@RequestParam(name = "q") String query) {

        return movieService.findByQueryOrderRating(query);
    }
}
