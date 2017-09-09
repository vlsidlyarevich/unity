package com.github.vlsidlyarevich.unity.mapping;

import com.github.vlsidlyarevich.unity.mapping.converter.TwitterUserUrlCustomConverter;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterProfileData;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterSubscriptionData;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import twitter4j.User;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

public class Twitter2TwitterData extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(User.class, TwitterSubscriptionData.class, oneWay())
                .fields("url", "url", FieldsMappingOptions.customConverter(TwitterUserUrlCustomConverter.class));

        mapping(User.class, TwitterProfileData.class, oneWay())
                .fields("url", "url", FieldsMappingOptions.customConverter(TwitterUserUrlCustomConverter.class));
    }
}
