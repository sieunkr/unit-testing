package com.example.demo.repository;

import com.example.demo.config.NaverProperties;
import com.example.demo.core.model.Movie;
import com.example.demo.core.repository.MovieRepository;
import com.example.demo.repository.response.ResponseMovie;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.OpenApiRuntimeException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final NaverProperties naverProperties;
    private final RestTemplate restTemplate;

    public MovieRepositoryImpl(NaverProperties naverProperties, RestTemplate restTemplate) {
        this.naverProperties = naverProperties;
        this.restTemplate = restTemplate;
    }

    public List<Movie> findByQuery(String query) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getMovieUrl() + "?query=" + query;

        try {

            return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                    .getBody()
                    .getItems()
                    .stream()
                    .map(m -> Movie.builder()
                            .title(m.getTitle())
                            .link(m.getLink())
                            .userRating(m.getUserRating())
                            .build())
                    .collect(Collectors.toList());

        } catch (HttpClientErrorException ex) {

            throw new OpenApiRuntimeException(ExceptionMessage.NAVER_API_ERROR);
        }
        //TODO: auth exception..
    }
}
