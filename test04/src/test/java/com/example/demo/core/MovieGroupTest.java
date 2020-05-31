package com.example.demo.core;

import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.response.ResponseMovie;
import com.example.demo.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MovieGroupTest {


    @Test
    @DisplayName("평점 순으로 정렬이 잘 되는지")
    void test_getListOrderRating() {

        //given
        float expectedUserRanking = 9.7f;
        MovieGroup movieGroup = new MovieGroup(getStubMovieList("query"));

        //when
        List<MovieDTO> actualList = movieGroup.getListOrderRating();

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }


    private List<MovieDTO> getStubMovieList(String query) {

        List<ResponseMovie.Item> items = Arrays.asList(
                ResponseMovie.Item.builder().title("<b>영화1</b> 제목").actor("배우1").userRating(9.3f).build(),
                ResponseMovie.Item.builder().title("<b>영화2</b> 제목").actor("배우2").userRating(9.7f).build(),
                ResponseMovie.Item.builder().title("<b>영화3</b> 제목").actor("배우3").userRating(0.0f).build(),
                ResponseMovie.Item.builder().title("<b>영화4</b> 제목").actor("배우4").userRating(7.5f).build()
        );

        ResponseMovie responseMovie = ResponseMovie.builder()
                .items(items)
                .build();

        return responseMovie.getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(m.getCleanTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}