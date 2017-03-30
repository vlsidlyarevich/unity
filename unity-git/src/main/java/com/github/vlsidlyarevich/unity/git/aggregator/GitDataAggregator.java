package com.github.vlsidlyarevich.unity.git.aggregator;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.git.populator.GitProfilePopulator;
import com.github.vlsidlyarevich.unity.git.populator.GitRepositoryPopulator;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GitDataAggregator {

    @Autowired
    private GitProfilePopulator gitProfilePopulator;

    @Autowired
    private GitRepositoryPopulator gitRepositoryPopulator;

    @Autowired
    private GitProfileService gitProfileService;

    @Autowired
    private GitRepositoryService gitRepositoryService;

    public Optional<GitProfileData> getGitProfileData(String gitLogin) {
        Optional<GitProfile> gitProfile = gitProfileService.getGitProfile(gitLogin);
        if (!gitProfile.isPresent()) {
            return Optional.empty();
        }
        return gitProfile.flatMap(this::aggregateData);
    }

    private Optional<GitProfileData> aggregateData(GitProfile gitProfile) {
        Optional<GitProfileData> result;
        result = Optional.of(gitProfilePopulator.populate(gitProfile));

        appendGitRepos(result.get());

        return result;
    }

    private void appendGitRepos(GitProfileData gitProfileData) {
        Optional<List<GitRepository>> gitRepositories = gitRepositoryService.getGitRepositories(gitProfileData.getLogin());
        if (!gitRepositories.isPresent()) {
            gitProfileData.setRepos(Optional.empty());
            return;
        }
        gitProfileData.setRepos(getRepositoryData(gitRepositories));
    }

    private Optional<List<GitRepositoryData>> getRepositoryData(Optional<List<GitRepository>> gitRepositories) {
        return gitRepositories.map(gitRepositories1 -> gitRepositories1
                .stream()
                .map(gitRepository -> gitRepositoryPopulator.populate(gitRepository))
                .collect(Collectors.toList()));
    }
}
