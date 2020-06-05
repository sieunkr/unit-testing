package com.example.demo.core.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Builder
@Getter
public class Movie {

    private String title;
    private String link;
    private float userRating;

    public String getTitle() {
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