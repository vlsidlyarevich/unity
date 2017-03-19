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
import java.util.Optional;


@Service
public class GitRepositoryServiceImpl implements GitRepositoryService {

    private static final Logger logger = LoggerFactory.getLogger(GitRepositoryServiceImpl.class);

    private final String gitApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    @Autowired
    public GitRepositoryServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl() + "users/{user}/repos";
    }

    @PostConstruct
    public void init() {
        this.restTemplate = restTemplateFactory.getObject();
    }

    public Optional<GitRepository[]> getGitRepositories(String gitProfile) {
        Optional<GitRepository[]> repositories;
        try {
            repositories = Optional.of(restTemplate.getForObject(gitApiUrl, GitRepository[].class, gitProfile));
        } catch (HttpClientErrorException e) {
            repositories = Optional.empty();
            logger.error("Can't get git repositories of profile: {gitProfile}", gitProfile);
        }
        return repositories;
    }
}
