package com.github.vlsidlyarevich.unity.mapping;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.mapping.converter.GitRepoLanguagesCustomConverter;
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
                .fields("subscriptions", "languages", FieldsMappingOptions
                        .customConverterId(GitRepoLanguagesCustomConverter.class.getSimpleName()));
    }
}
