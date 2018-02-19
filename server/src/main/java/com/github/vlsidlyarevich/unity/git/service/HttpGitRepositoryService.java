package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.factory.RestTemplateFactory;
import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class HttpGitRepositoryService implements GitRepositoryService {

    private static final String LINK_HEADER_NAME = "Link";
    private static final String DELIM_LINKS = ",";
    private static final String DELIM_LINK_PARAM = ";";
    private static final int PER_PAGE_LIMIT = 100;

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
            log.error("Can't initiate rest template factory with error: {},"
                    + " using default one.", e.getCause());
            this.restTemplate = new RestTemplate();
        }
    }

    public Optional<List<GitRepository>> getGitRepositories(final String gitProfile) {
        Optional<List<GitRepository>> repositories = Optional.empty();

        final Map<String, String> variables = Collections.singletonMap("user", gitProfile);

        try {
            int page = 1;

            final ResponseEntity<GitRepository[]> response = restTemplate
                    .getForEntity(String.format("%s?per_page=%d", gitUserRepositoriesUrl, PER_PAGE_LIMIT),
                            GitRepository[].class, variables);

            final List<GitRepository> result = new ArrayList<>(Arrays.asList(response.getBody()));

            int pagesTotal = getPagesTotal(response.getHeaders().get(LINK_HEADER_NAME));

            while (page++ != pagesTotal) {
                final String requestUrl = String
                        .format("%s?per_page=%d&page=%d", gitUserRepositoriesUrl, PER_PAGE_LIMIT, page);

                final ResponseEntity<GitRepository[]> additionalResponse
                        = restTemplate.getForEntity(requestUrl, GitRepository[].class, variables);

                result.addAll(Arrays.asList(additionalResponse.getBody()));
            }

            repositories = Optional.of(result);
        } catch (HttpClientErrorException e) {
            log.error("Can't get git repositories of profile: {} with error {}",
                    gitProfile, e.getMessage());
        }

        return repositories;
    }

    public Optional<GitRepository> getGitRepository(final String gitProfile, final String repo) {
        Optional<GitRepository> repositories = Optional.empty();

        try {
            repositories = Optional.of(restTemplate
                    .getForObject(gitRepositoryUrl, GitRepository.class, gitProfile, repo));
        } catch (HttpClientErrorException e) {
            log.error("Can't get git repository: {} of profile: {} with error {}",
                    repo, gitProfile, e.getMessage());
        }

        return repositories;
    }

    private int getPagesTotal(final List<String> linkHeader) {
        if (CollectionUtils.isNotEmpty(linkHeader)) {
            final String linkString = linkHeader.get(0);

            final String[] links = linkString.split(DELIM_LINKS);

            for (String link : links) {
                String[] segments = link.split(DELIM_LINK_PARAM);
                if (segments.length < 2) {
                    continue;
                }

                final String rel = segments[1];
                final String relValue = rel.trim().split("=")[1];
                if ("\"last\"".equals(relValue)) {
                    String linkPart = segments[0].trim();
                    linkPart = linkPart.substring(1, linkPart.length() - 1);

                    return Integer.parseInt(linkPart.split("&page=")[1]);
                }
            }
        }

        return 1;
    }
}
