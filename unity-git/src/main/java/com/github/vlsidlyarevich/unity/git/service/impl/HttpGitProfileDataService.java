package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.aggregator.GitDataAggregator;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.service.GitProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HttpGitProfileDataService implements GitProfileDataService {

    @Autowired
    private GitDataAggregator gitDataAggregator;

    @Override
    public Optional<GitProfileData> getGitProfileData(String gitLogin) {
        if (gitLogin != null) {
            return gitDataAggregator.getGitProfileData(gitLogin);
        } else return Optional.empty();
    }
}
