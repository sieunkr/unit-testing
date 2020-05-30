package com.example.demo.service;

import com.example.demo.config.NaverProperties;
import com.example.demo.core.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.response.ResponseMovie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MovieServiceTest {

    private MovieService movieService;

    @Test
    @DisplayName("사용자 평점 순으로 정렬이 잘 되는지")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.7f;
        MockMovieRepository mockRepository = new MockMovieRepository(null, null);
        movieService = new MovieService(mockRepository);

        //when
        List<MovieDTO> actualList = movieService.findByQuery("쿼리");

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }


    static class MockMovieRepository extends MovieRepository {

        public MockMovieRepository(NaverProperties naverProperties, RestTemplate restTemplate) {
            super(naverProperties, restTemplate);
        }

        @Override
        public ResponseMovie findByQuery(String query) {

            //return super.findByQuery(query);
            //가짜 데이터를 전달
            List<ResponseMovie.Item> items = Arrays.asList(
                    ResponseMovie.Item.builder().title("<b>영화1</b> 제목").actor("배우1").userRating(9.3f).build(),
                    ResponseMovie.Item.builder().title("<b>영화2</b> 제목").actor("배우2").userRating(9.7f).build(),
                    ResponseMovie.Item.builder().title("<b>영화3</b> 제목").actor("배우3").userRating(0.0f).build(),
                    ResponseMovie.Item.builder().title("<b>영화4</b> 제목").actor("배우4").userRating(7.5f).build()
            );

            return ResponseMovie.builder()
                    .items(items)
                    .build();
        }
    }
}