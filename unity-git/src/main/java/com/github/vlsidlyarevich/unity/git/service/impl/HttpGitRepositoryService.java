package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HttpGitRepositoryService implements GitRepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(HttpGitRepositoryService.class);

    private final String gitUserRepositoriesUrl;
    private final String gitRepositoryUrl;

    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    @Autowired
    public HttpGitRepositoryService(GitProperties gitProperties) {
        this.gitUserRepositoriesUrl = gitProperties.getApiUrl() + "/users/{user}/repos";
        this.gitRepositoryUrl = gitProperties.getApiUrl() + "/repos/{user}/{repo}";
    }

    @PostConstruct
    public void init() {
        try {
            this.restTemplate = restTemplateFactory.getObject();
        } catch (Exception e) {
            logger.error("Can't initiate rest template factory with error: {}, using default one.", e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    public Optional<List<GitRepository>> getGitRepositories(String gitProfile) {
        Optional<List<GitRepository>> repositories;
        try {
            repositories = Optional.of(Arrays.asList(restTemplate.getForObject(gitUserRepositoriesUrl, GitRepository[].class, gitProfile)));
        } catch (HttpClientErrorException e) {
            repositories = Optional.empty();
            logger.error("Can't get git repositories of profile: {} with error {}", gitProfile, e.getMessage());
        }
        return repositories;
    }

    public Optional<GitRepository> getGitRepository(String gitProfile, String repo) {
        Optional<GitRepository> repositories;
        try {
            repositories = Optional.of(restTemplate.getForObject(gitRepositoryUrl, GitRepository.class, gitProfile, repo));
        } catch (HttpClientErrorException e) {
            repositories = Optional.empty();
            logger.error("Can't get git repository: {} of profile: {} with error {}", repo, gitProfile, e.getMessage());
        }
        return repositories;
    }
}
