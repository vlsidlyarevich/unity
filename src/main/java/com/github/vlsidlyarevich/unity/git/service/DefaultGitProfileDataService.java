package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.aggregator.GitDataAggregator;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultGitProfileDataService implements GitProfileDataService {

    private final GitDataAggregator gitDataAggregator;

    @Autowired
    public DefaultGitProfileDataService(final GitDataAggregator gitDataAggregator) {
        this.gitDataAggregator = gitDataAggregator;
    }

    @Override
    public Optional<GitProfileData> getGitProfileData(final String gitLogin) {
        return gitLogin != null ? gitDataAggregator.getGitProfileData(gitLogin) : Optional.empty();
    }
}
