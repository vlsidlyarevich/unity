package com.github.vlsidlyarevich.unity.mapping;

import com.github.vlsidlyarevich.unity.twitter.model.TwitterSubscriptionData;
import org.dozer.loader.api.BeanMappingBuilder;
import twitter4j.User;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

public class Twitter2TwitterData extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(User.class, TwitterSubscriptionData.class, oneWay())
                .fields("url", "url");
    }
}
