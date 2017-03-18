package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.service.GitProfileRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class GitProfileRepositoryServiceImpl implements GitProfileRepositoryService {

    private final String gitApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    @Autowired
    public GitProfileRepositoryServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl() + "users/{user}/repos";
    }

    @PostConstruct
    public void init() {
        this.restTemplate = restTemplateFactory.getObject();
    }

    public GitRepository[] getGitRepositories(String gitProfile) {
        return restTemplate.getForObject(gitApiUrl, GitRepository[].class, gitProfile);
    }
}
