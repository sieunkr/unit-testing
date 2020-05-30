package com.example.demo.repository.response;

import lombok.*;
import org.springframework.util.StringUtils;

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

        public String getCleanTitle() {
            return removeSpecialCharacter(title);
        }

        //TODO: 코드 좀 더 깔끔하게 변경 가능한지?
        private String removeSpecialCharacter(String str) {

            String resultStr = str;
            resultStr = StringUtils.replace(resultStr, "<b>", "");
            resultStr = StringUtils.replace(resultStr, "</b>", "");
            return resultStr;
        }
    }
}
