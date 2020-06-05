package com.example.demo.service;

import com.example.demo.core.model.Movie;
import com.example.demo.repository.MovieRepositoryImpl;
import com.example.demo.repository.response.ResponseMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = MovieService.class)
class MovieServiceSpringBootTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepositoryImpl movieRepository;


    @Test
    @DisplayName("평점 순으로 정렬이 잘 되는지")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.7f;
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<Movie> actualList = movieService.findByQueryOrderRating("쿼리");

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }

    private List<Movie> getStubMovieList() {

        return Arrays.asList(
                Movie.builder().title("<b>영화1</b> 제목").userRating(9.3f).build(),
                Movie.builder().title("<b>영화2</b> 제목").userRating(9.7f).build(),
                Movie.builder().title("<b>영화3</b> 제목").userRating(0.0f).build(),
                Movie.builder().title("<b>영화4</b> 제목").userRating(7.5f).build()
        );
    }
}