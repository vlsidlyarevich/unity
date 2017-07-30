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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        gitProfileData.setLanguagesTotal(calculateLanguagesTotal(repositoryDataList));
    }

    private void appendForkLanguagesTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(GitRepositoryData::getIsFork)
                        .collect(Collectors.toList());

        gitProfileData.setForksLanguagesTotal(calculateLanguagesTotal(repositoryDataList));
    }

    private void appendTopicsTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(gitRepositoryData -> !gitRepositoryData.getIsFork())
                        .collect(Collectors.toList());

        gitProfileData.setTopicsTotal(calculateTopicsTotal(repositoryDataList));
    }

    private void appendForkTopicsTotal(final GitProfileData gitProfileData) {
        final List<GitRepositoryData> repositoryDataList =
                gitProfileData.getRepos()
                        .stream()
                        .filter(GitRepositoryData::getIsFork)
                        .collect(Collectors.toList());

        gitProfileData.setForksTopicsTotal(calculateTopicsTotal(repositoryDataList));
    }

    private Map<String, Integer> calculateLanguagesTotal(
            final List<GitRepositoryData> gitRepositoryDataList) {
        final Map<String, Integer> languagesTotal = new HashMap<>();

        gitRepositoryDataList
                .stream()
                .map(GitRepositoryData::getLanguages)
                .forEach(languagesList -> languagesList.forEach((languageName, languageCounter) -> {
                    languagesTotal.computeIfPresent(languageName,
                            (key, value) -> value + Integer.valueOf(languageCounter));
                    languagesTotal.putIfAbsent(languageName, Integer.valueOf(languageCounter));
                }));

        return languagesTotal;
    }

    private Map<String, Integer> calculateTopicsTotal(
            final List<GitRepositoryData> gitRepositoryDataList) {
        final Map<String, Integer> topicsTotal = new HashMap<>();

        gitRepositoryDataList.stream()
                .map(GitRepositoryData::getTopics)
                .forEach(languagesList -> languagesList.forEach((topicName) -> {
                    topicsTotal.computeIfPresent(topicName, (key, value) -> value + 1);
                    topicsTotal.putIfAbsent(topicName, 1);
                }));

        return topicsTotal;
    }
}
