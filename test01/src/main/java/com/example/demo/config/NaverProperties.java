package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "naver.openapi")
public class NaverProperties {

    private String blogUrl;
    private String movieUrl;
    private String clientId;
    private String clientSecret;
}
