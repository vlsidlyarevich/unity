package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.service.GitProfileRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitProfileRepositoryServiceImpl implements GitProfileRepositoryService {

    private final String gitApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public GitProfileRepositoryServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl() + "/{user}/repos";
    }

    public GitRepository[] getGitRepositories(String gitProfile) {
        return restTemplate.getForObject(gitApiUrl, GitRepository[].class, gitProfile);
    }
}
