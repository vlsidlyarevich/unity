package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
public class HttpGitProfileService implements GitProfileService {

    private final String gitApiUrl;

    private RestTemplate restTemplate;

    private final RestTemplateFactory restTemplateFactory;

    @Autowired
    public HttpGitProfileService(final GitProperties gitProperties,
                                 final RestTemplateFactory restTemplateFactory) {
        this.gitApiUrl = gitProperties.getApiUrl() + "/users/{user}";
        this.restTemplateFactory = restTemplateFactory;
    }

    @PostConstruct
    public void init() {
        try {
            this.restTemplate = restTemplateFactory.getObject();
        } catch (Exception e) {
            log.error("Can't initiate rest template factory with error: {}, using default one.",
                    e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    public Optional<GitProfile> getGitProfile(final String gitProfile) {
        Optional<GitProfile> result;
        try {
            result = Optional.of(restTemplate.getForObject(gitApiUrl,
                    GitProfile.class, gitProfile));
        } catch (HttpClientErrorException e) {
            result = Optional.empty();
            log.error("Can't find git profile: {} with error {}", gitProfile, e.getMessage());
        }
        return result;
    }
}
