package com.example.demo.core;

import com.example.demo.core.model.Movie;
import com.example.demo.core.model.MovieGroup;
import com.example.demo.repository.response.ResponseMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieGroupTest {


    @Test
    @DisplayName("평점 순으로 정렬이 잘 되는지")
    void test_getListOrderRating() {

        //given
        float expectedUserRanking = 9.7f;
        MovieGroup movieGroup = new MovieGroup(getStubMovieList("query"));

        //when
        List<Movie> actualList = movieGroup.getListOrderRating();

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }


    private List<Movie> getStubMovieList(String query) {

        return Arrays.asList(
                Movie.builder().title("<b>영화1</b> 제목").userRating(9.3f).build(),
                Movie.builder().title("<b>영화2</b> 제목").userRating(9.7f).build(),
                Movie.builder().title("<b>영화3</b> 제목").userRating(0.0f).build(),
                Movie.builder().title("<b>영화4</b> 제목").userRating(7.5f).build()
        );
    }
}