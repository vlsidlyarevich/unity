package com.github.vlsidlyarevich.unity.git.service.impl;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryLanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Service
public class GitRepositoryLanguageServiceImpl implements GitRepositoryLanguageService {

    private static final Logger logger = LoggerFactory.getLogger(GitRepositoryLanguageServiceImpl.class);

    private final String gitApiUrl;

    private RestTemplate restTemplate;

    @Autowired
    private FactoryBean<RestTemplate> restTemplateFactory;

    @PostConstruct
    public void init() {
        try {
            this.restTemplate = restTemplateFactory.getObject();
        } catch (Exception e) {
            logger.error("Can't initiate rest template factory with error: {}, using default one.", e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    @Autowired
    public GitRepositoryLanguageServiceImpl(GitProperties gitProperties) {
        this.gitApiUrl = gitProperties.getApiUrl() + "/repos/{user}/{repo}/languages";
    }

    @Override
    public Optional<Map<String, String>> getGitRepoLanguages(String url) {
        return null;
    }

    @Override
    public Optional<Map<String, String>> getGitRepoLanguages(String gitProfile, String repo) {
        return null;
    }
}
