package com.github.vlsidlyarevich.unity.twitter.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Twitter4jConfig {

    private final TwitterProperties properties;

    @Bean
    public Twitter twitterClient() {
        final ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getConsumerKey())
                .setOAuthConsumerSecret(properties.getConsumerSecret())
                .setOAuthAccessToken(properties.getAccessToken())
                .setOAuthAccessTokenSecret(properties.getAccessTokenSecret());

        final TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
