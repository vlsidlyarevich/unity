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

    private final GitProfilePopulator gitProfilePopulator;

    private final GitRepositoryPopulator gitRepositoryPopulator;

    private final GitProfileService gitProfileService;

    private final GitRepositoryService gitRepositoryService;

    @Autowired
    public GitDataAggregator(final GitProfilePopulator gitProfilePopulator,
                             final GitRepositoryPopulator gitRepositoryPopulator,
                             final GitProfileService gitProfileService,
                             final GitRepositoryService gitRepositoryService) {
        this.gitProfilePopulator = gitProfilePopulator;
        this.gitRepositoryPopulator = gitRepositoryPopulator;
        this.gitProfileService = gitProfileService;
        this.gitRepositoryService = gitRepositoryService;
    }

    public Optional<GitProfileData> getGitProfileData(final String gitLogin) {
        Optional<GitProfile> gitProfile = gitProfileService.getGitProfile(gitLogin);
        if (!gitProfile.isPresent()) {
            return Optional.empty();
        }
        return gitProfile.flatMap(this::aggregateData);
    }

    private Optional<GitProfileData> aggregateData(final GitProfile gitProfile) {
        Optional<GitProfileData> result;
        result = Optional.of(gitProfilePopulator.populate(gitProfile));

        appendGitRepos(result.get());

        return result;
    }

    private void appendGitRepos(final GitProfileData gitProfileData) {
        Optional<List<GitRepository>> gitRepositories
                = gitRepositoryService.getGitRepositories(gitProfileData.getLogin());
        if (gitRepositories.isPresent()) {
            gitProfileData.setRepos(getRepositoryData(gitRepositories).get());
        }
    }

    private Optional<List<GitRepositoryData>> getRepositoryData(
            final Optional<List<GitRepository>> gitRepositories) {
        return gitRepositories.map(gitRepositories1 -> gitRepositories1
                .stream()
                .map(gitRepository -> gitRepositoryPopulator.populate(gitRepository))
                .collect(Collectors.toList()));
    }
}
