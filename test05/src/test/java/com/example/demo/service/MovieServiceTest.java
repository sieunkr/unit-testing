package com.example.demo.service;

import com.example.demo.core.model.Movie;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.MovieRepositoryImpl;
import com.example.demo.repository.response.ResponseMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private MovieService movieService;

    @Mock
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


    @Test
    @DisplayName("평점이 0인 데이터는 제외하는지")
    void user_ratings_exclude_zero() {

        //given
        int expectedMovieSize = 3;
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<Movie> actualList = movieService.findByQueryOrderRating("쿼리");

        //then
        assertEquals(expectedMovieSize, actualList.size());

    }


    @Test
    @DisplayName("<b>, </b> 제거 잘 하는지")
    void remove_special_characters_when_mapping_title() {

        //given
        int expectedSpecialCharacterCount = 0;
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<Movie> actualList = movieService.findByQuery("쿼리");

        //then
        assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(actualList.stream().findFirst().get().getTitle(), "<b>"));

        assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(actualList.stream().findFirst().get().getTitle(), "</b>"));

    }

    @Test
    @DisplayName("상위 2개 영화의 평점 평균 계산을 잘 하는지?")
    void test_calculateAverageUserRating() {

        //given
        double expectedAverage = 9.5;
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        double actualAverage = movieService.calculateAverageUserRating("쿼리");

        //then
        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    @DisplayName("쿼리가 없을 때 BAD_REQUEST_QUERY_ERROR 처리를 잘 하는지")
    void givenEmptyQuery_whenFindByQuery_thenThrowException() {

        //given
        String inputQuery = "";
        movieService = new MovieService(movieRepository);

        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> movieService.findByQueryOrderRating(inputQuery));

        //then
        assertEquals(ExceptionMessage.BAD_REQUEST_QUERY_EMPTY.getMessage(), exception.getMessage());

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