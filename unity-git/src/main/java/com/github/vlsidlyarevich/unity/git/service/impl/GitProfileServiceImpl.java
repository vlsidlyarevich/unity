package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitProfileServiceImpl implements GitProfileService {

    private final String gitApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public GitProfileServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl();
    }

    public GitProfile getGitProfile(String gitProfile) {
        return restTemplate.getForObject(gitApiUrl + '/' + gitProfile, GitProfile.class);
    }
}
