package com.example.demo.repository.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMovie {

    private List<Item> items;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String title;
        private String link;
        private String actor;
        private String director;
        private float userRating;
        //...TODO: 필드 추가
    }
}
