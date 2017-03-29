package com.github.vlsidlyarevich.unity.git.populator.impl;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.git.populator.GitRepositoryPopulator;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitRepositoryPopulatorImpl implements GitRepositoryPopulator {

    @Autowired
    private GitRepositoryLanguageService gitRepositoryLanguageService;


    public GitRepositoryData populate(GitRepository gitRepository) {
        GitRepositoryData result = new GitRepositoryData();
        result.setId(gitRepository.getId());
        //TODO


        return result;
    }

    @Override
    public boolean canPopulate(Object model) {
        return model instanceof GitRepository;
    }
}

//    private String id;
//    private String name;
//    private String fullName;
//    private GitRepoOwnerData owner;
//    private Boolean isPrivate;
//    private String htmlUrl;
//    private String description;
//    private Boolean isFork;
//    private String url;
//    private String forksUrl;
//    private String keysUrl;
//    private String collaboratorsUrl;
//    private String teamsUrl;
//    private String hooksUrl;
//    private String issueEventsUrl;
//    private String eventsUrl;
//    private String assigneesUrl;
//    private String branchesUrl;
//    private String tagsUrl;
//    private String blobsUrl;
//    private String gitTagsUrl;
//    private String gitRefsUrl;
//    private String treesUrl;
//    private String statusesUrl;
//    private Map<String, String> languages;
//    private String stargazersUrl;
//    private String contributorsUrl;
//    private String subscribersUrl;
//    private String subscriptionUrl;
//    private String commitsUrl;
//    private String gitCommitsUrl;
//    private String commentsUrl;
//    private String issueCommentUrl;
//    private String contentsUrl;
//    private String compareUrl;
//    private String mergesUrl;
//    private String archiveUrl;
//    private String downloadsUrl;
//    private String issuesUrl;
//    private String pullsUrl;
//    private String milestonesUrl;
//    private String notificationsUrl;
//    private String labelsUrl;
//    private String releasesUrl;
//    private String deploymentsUrl;
//    private Date createdAt;
//    private Date updatedAt;
//    private Date pushedAt;
//    private String gitUrl;
//    private String sshUrl;
//    private String svnUrl;
//    private String homepage;
//    private Integer size;
//    private Integer stargazersCount;
//    private Integer watchersCount;
//    private String language;
//    private Boolean hasIssues;
//    private Boolean hasDownloads;
//    private Boolean hasWiki;
//    private Boolean hasPages;
//    private Integer forksCount;
//    private String mirrorUrl;
//    private Integer openIssuesCount;
//    private Integer forks;
//    private Integer openIssues;
//    private Integer watchers;
//    private String defaultBranch;
//    private Integer networkCount;
//    private Integer subscribersCount;