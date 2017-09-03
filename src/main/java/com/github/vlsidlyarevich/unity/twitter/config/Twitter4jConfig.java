package com.github.vlsidlyarevich.unity.twitter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class Twitter4jConfig {

    @Value("twitter.oauth.consumerKey")
    private String consumerKey;

    @Value("twitter.oauth.consumerSecret")
    private String consumerSecret;

    @Value("twitter.oauth.accessToken")
    private String accessToken;

    @Value("twitter.oauth.accessTokenSecret")
    private String tokenSecret;

    @Bean
    public Twitter twitterClient() {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(tokenSecret);

        final TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
