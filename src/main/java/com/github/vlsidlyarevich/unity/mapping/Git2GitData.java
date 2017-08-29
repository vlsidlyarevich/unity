package com.github.vlsidlyarevich.unity.mapping;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;

import static org.dozer.loader.api.TypeMappingOptions.oneWay;

public class Git2GitData extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(GitRepository.class, GitRepositoryData.class, oneWay())
                .fields("languagesUrl", "languages", FieldsMappingOptions
                        .customConverterId("gitRepoLanguagesCustomConverter"));
    }
}
