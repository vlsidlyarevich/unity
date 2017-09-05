package com.github.vlsidlyarevich.unity.twitter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "twitter")
public class TwitterProperties {

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
}
