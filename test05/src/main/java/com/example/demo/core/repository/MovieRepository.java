package com.example.demo.core.repository;

import com.example.demo.core.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
