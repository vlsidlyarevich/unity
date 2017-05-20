package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HttpGitRepositoryService implements GitRepositoryService {

    private final String gitUserRepositoriesUrl;
    private final String gitRepositoryUrl;

    private RestTemplate restTemplate;

    private final RestTemplateFactory restTemplateFactory;

    @Autowired
    public HttpGitRepositoryService(final GitProperties gitProperties,
                                    final RestTemplateFactory restTemplateFactory) {
        this.gitUserRepositoriesUrl = gitProperties.getApiUrl() + "/users/{user}/repos";
        this.gitRepositoryUrl = gitProperties.getApiUrl() + "/repos/{user}/{repo}";
        this.restTemplateFactory = restTemplateFactory;
    }

    @PostConstruct
    public void init() {
        try {
            this.restTemplate = restTemplateFactory.getObject();
        } catch (Exception e) {
            log.error("Can't initiate rest template factory with error: {}," +
                    " using default one.", e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    public Optional<List<GitRepository>> getGitRepositories(final String gitProfile) {
        Optional<List<GitRepository>> repositories;
        try {
            repositories = Optional.of(Arrays.asList(restTemplate
                    .getForObject(gitUserRepositoriesUrl, GitRepository[].class, gitProfile)));
        } catch (HttpClientErrorException e) {
            repositories = Optional.empty();
            log.error("Can't get git repositories of profile: {} with error {}",
                    gitProfile, e.getMessage());
        }
        return repositories;
    }

    public Optional<GitRepository> getGitRepository(final String gitProfile, final String repo) {
        Optional<GitRepository> repositories;
        try {
            repositories = Optional.of(restTemplate
                    .getForObject(gitRepositoryUrl, GitRepository.class, gitProfile, repo));
        } catch (HttpClientErrorException e) {
            repositories = Optional.empty();
            log.error("Can't get git repository: {} of profile: {} with error {}",
                    repo, gitProfile, e.getMessage());
        }
        return repositories;
    }
}
