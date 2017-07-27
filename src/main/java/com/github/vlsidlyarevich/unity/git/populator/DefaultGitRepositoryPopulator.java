package com.github.vlsidlyarevich.unity.git.populator;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.git.service.GitRepositoryLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DefaultGitRepositoryPopulator implements GitRepositoryPopulator {

    private final GitRepositoryLanguageService gitRepositoryLanguageService;

    private final GitRepoOwnerPopulator gitRepoOwnerPopulator;

    @Autowired
    public DefaultGitRepositoryPopulator(
            final GitRepositoryLanguageService gitRepositoryLanguageService,
            final GitRepoOwnerPopulator gitRepoOwnerPopulator) {
        this.gitRepositoryLanguageService = gitRepositoryLanguageService;
        this.gitRepoOwnerPopulator = gitRepoOwnerPopulator;
    }

    public GitRepositoryData populate(final GitRepository gitRepository) {
        GitRepositoryData result = new GitRepositoryData();

        result.setId(gitRepository.getId());
        result.setName(gitRepository.getName());
        result.setFullName(gitRepository.getFullName());

        result.setOwner(gitRepoOwnerPopulator.populate(gitRepository.getOwner()));

        result.setIsPrivate(gitRepository.getIsPrivate());
        result.setHtmlUrl(gitRepository.getHtmlUrl());
        result.setDescription(gitRepository.getDescription());
        result.setIsFork(gitRepository.getIsFork());
        result.setUrl(gitRepository.getUrl());
        result.setForksUrl(gitRepository.getForksUrl());
        result.setKeysUrl(gitRepository.getKeysUrl());
        result.setCollaboratorsUrl(gitRepository.getCollaboratorsUrl());
        result.setTeamsUrl(gitRepository.getTeamsUrl());
        result.setHooksUrl(gitRepository.getHooksUrl());
        result.setIssueEventsUrl(gitRepository.getIssueEventsUrl());
        result.setEventsUrl(gitRepository.getEventsUrl());
        result.setAssigneesUrl(gitRepository.getAssigneesUrl());
        result.setBranchesUrl(gitRepository.getBranchesUrl());
        result.setTagsUrl(gitRepository.getTagsUrl());
        result.setBlobsUrl(gitRepository.getBlobsUrl());
        result.setGitTagsUrl(gitRepository.getGitTagsUrl());
        result.setGitRefsUrl(gitRepository.getGitRefsUrl());
        result.setTreesUrl(gitRepository.getTreesUrl());
        result.setStatusesUrl(gitRepository.getStatusesUrl());
        result.setTopics(gitRepository.getTopics());

        result.setLanguages(gitRepositoryLanguageService
                .getGitRepoLanguages(gitRepository.getLanguagesUrl())
                .orElse(new HashMap<>()));

        result.setStargazersUrl(gitRepository.getStargazersUrl());
        result.setContributorsUrl(gitRepository.getContributorsUrl());
        result.setSubscribersUrl(gitRepository.getSubscribersUrl());
        result.setSubscriptionUrl(gitRepository.getSubscriptionUrl());
        result.setCommitsUrl(gitRepository.getCommitsUrl());
        result.setGitCommitsUrl(gitRepository.getGitCommitsUrl());
        result.setCommentsUrl(gitRepository.getCommentsUrl());
        result.setIssueCommentUrl(gitRepository.getIssueCommentUrl());
        result.setContentsUrl(gitRepository.getContentsUrl());
        result.setCompareUrl(gitRepository.getCompareUrl());
        result.setMergesUrl(gitRepository.getMergesUrl());
        result.setArchiveUrl(gitRepository.getArchiveUrl());
        result.setDownloadsUrl(gitRepository.getDownloadsUrl());
        result.setIssuesUrl(gitRepository.getIssuesUrl());
        result.setPullsUrl(gitRepository.getPullsUrl());
        result.setMilestonesUrl(gitRepository.getMilestonesUrl());
        result.setNotificationsUrl(gitRepository.getNotificationsUrl());
        result.setLabelsUrl(gitRepository.getLabelsUrl());
        result.setReleasesUrl(gitRepository.getReleasesUrl());
        result.setDeploymentsUrl(gitRepository.getDeploymentsUrl());
        result.setCreatedAt(gitRepository.getCreatedAt());
        result.setUpdatedAt(gitRepository.getUpdatedAt());
        result.setPushedAt(gitRepository.getPushedAt());
        result.setGitUrl(gitRepository.getGitUrl());
        result.setSshUrl(gitRepository.getSshUrl());
        result.setSvnUrl(gitRepository.getSvnUrl());
        result.setHomepage(gitRepository.getHomepage());
        result.setSize(gitRepository.getSize());
        result.setStargazersCount(gitRepository.getStargazersCount());
        result.setWatchersCount(gitRepository.getWatchersCount());
        result.setLanguage(gitRepository.getLanguage());
        result.setHasIssues(gitRepository.getHasIssues());
        result.setHasDownloads(gitRepository.getHasDownloads());
        result.setHasWiki(gitRepository.getHasWiki());
        result.setHasPages(gitRepository.getHasPages());
        result.setForksCount(gitRepository.getForksCount());
        result.setMirrorUrl(gitRepository.getMirrorUrl());
        result.setOpenIssuesCount(gitRepository.getOpenIssuesCount());
        result.setForks(gitRepository.getForks());
        result.setOpenIssues(gitRepository.getOpenIssues());
        result.setWatchers(gitRepository.getWatchers());
        result.setDefaultBranch(gitRepository.getDefaultBranch());
        result.setNetworkCount(gitRepository.getNetworkCount());
        result.setSubscribersCount(gitRepository.getSubscribersCount());

        return result;
    }

    @Override
    public boolean canPopulate(final Object model) {
        return model instanceof GitRepository;
    }
}
