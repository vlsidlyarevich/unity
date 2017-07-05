package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class HttpGitRepositoryLanguageService implements GitRepositoryLanguageService {

    private final String gitApiUrl;

    private RestTemplate restTemplate;

    private final RestTemplateFactory restTemplateFactory;

    @Autowired
    public HttpGitRepositoryLanguageService(final GitProperties gitProperties,
                                            final RestTemplateFactory restTemplateFactory) {
        this.gitApiUrl
                = String.format("%s/repos/{user}/{repo}/languages", gitProperties.getApiUrl());
        this.restTemplateFactory = restTemplateFactory;
    }

    @PostConstruct
    public void init() {
        try {
            this.restTemplate = restTemplateFactory.getObject();
        } catch (Exception e) {
            log.error("Can't initiate rest template factory with error: {},"
                    + " using default one.", e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    @Override
    public Optional<Map<String, String>> getGitRepoLanguages(final String url) {
        Optional<Map<String, String>> result = Optional.empty();
        ParameterizedTypeReference<Map<String, String>> response
                = new ParameterizedTypeReference<Map<String, String>>() {
        };

        try {
            result = Optional.of(restTemplate.exchange(url, HttpMethod.GET,
                    null, response).getBody());
        } catch (HttpClientErrorException e) {
            log.error("Can't get languages by url: {} with error {}", url, e.getMessage());
        }

        return result;
    }

    @Override
    public Optional<Map<String, String>> getGitRepoLanguages(final String gitProfile,
                                                             final String repo) {
        Optional<Map<String, String>> result = Optional.empty();
        ParameterizedTypeReference<Map<String, String>> response
                = new ParameterizedTypeReference<Map<String, String>>() {
        };

        try {
            result = Optional.of(restTemplate.exchange(gitApiUrl, HttpMethod.GET,
                    null, response, gitProfile, repo).getBody());
        } catch (HttpClientErrorException e) {
            log.error("Can't get languages of git profile's:{} repo {} with error {}",
                    gitProfile, repo, e.getMessage());
        }

        return result;
    }
}
