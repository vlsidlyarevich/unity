package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.aggregator.GitDataAggregator;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultGitProfileDataService implements GitProfileDataService {

    private final GitDataAggregator gitDataAggregator;

    @Override
    public Optional<GitProfileData> getData(final String gitLogin) {
        return Optional.ofNullable(gitLogin)
                .map(login -> gitDataAggregator.getGitProfileData(gitLogin))
                .orElseThrow(() -> new IllegalArgumentException("Git login should not be empty"));
    }
}
