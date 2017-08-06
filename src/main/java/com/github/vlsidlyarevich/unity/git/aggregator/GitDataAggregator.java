package com.github.vlsidlyarevich.unity.git.aggregator;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.git.populator.GitProfilePopulator;
import com.github.vlsidlyarevich.unity.git.populator.GitRepositoryPopulator;
import com.github.vlsidlyarevich.unity.git.service.GitDataTotalCalculator;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GitDataAggregator {

    private final GitProfilePopulator gitProfilePopulator;

    private final GitRepositoryPopulator gitRepositoryPopulator;

    private final GitProfileService gitProfileService;

    private final GitRepositoryService gitRepositoryService;

    private final GitDataTotalCalculator gitRepositoryLanguagesTotalCalculator;

    private final GitDataTotalCalculator gitRepositoryTopicsTotalCalculator;

    public Optional<GitProfileData> getGitProfileData(final String gitLogin) {
        Optional<GitProfile> gitProfile = gitProfileService.getGitProfile(gitLogin);
        return gitProfile.flatMap(this::aggregateData);
    }

    private Optional<GitProfileData> aggregateData(final GitProfile gitProfile) {
        final Optional<GitProfileData> result
                = Optional.of(gitProfilePopulator.populate(gitProfile));

        result.ifPresent(gitProfileData -> {
            appendGitRepos(gitProfileData);
            appendLanguagesTotal(gitProfileData);
            appendForkLanguagesTotal(gitProfileData);
            appendTopicsTotal(gitProfileData);
            appendForkTopicsTotal(gitProfileData);
        });

        return result;
    }

    private void appendGitRepos(final GitProfileData gitProfileData) {
        final Optional<List<GitRepository>> gitRepositories
                = gitRepositoryService.getGitRepositories(gitProfileData.getLogin());
        gitRepositories.ifPresent(gitRepos ->
                gitProfileData.setRepos(getRepositoryData(gitRepos))
        );
    }

    private List<GitRepositoryData> getRepositoryData(
            final List<GitRepository> gitRepositories) {
        return gitRepositories.stream()
                .map(gitRepositoryPopulator::populate)
                .collect(Collectors.toList());
    }

    private void appendLanguagesTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(gitRepositoryData -> !gitRepositoryData.getIsFork())
                        .collect(Collectors.toList());

        gitProfileData.setLanguagesTotal(gitRepositoryLanguagesTotalCalculator.calculateTotal(
                repositoryDataList));
    }

    private void appendForkLanguagesTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(GitRepositoryData::getIsFork)
                        .collect(Collectors.toList());

        gitProfileData.setForksLanguagesTotal(gitRepositoryLanguagesTotalCalculator.calculateTotal(
                repositoryDataList));
    }

    private void appendTopicsTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(gitRepositoryData -> !gitRepositoryData.getIsFork())
                        .collect(Collectors.toList());

        gitProfileData.setTopicsTotal(gitRepositoryTopicsTotalCalculator
                .calculateTotal(repositoryDataList));
    }

    private void appendForkTopicsTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(GitRepositoryData::getIsFork)
                        .collect(Collectors.toList());

        gitProfileData.setForksTopicsTotal(gitRepositoryTopicsTotalCalculator
                .calculateTotal(repositoryDataList));
    }
}
