package com.github.vlsidlyarevich.unity.git.populator.impl;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.populator.GitProfilePopulator;
import org.springframework.stereotype.Component;

@Component
public class DefaultGitProfilePopulator implements GitProfilePopulator {

    public GitProfileData populate(final GitProfile gitProfile) {
        GitProfileData result = new GitProfileData();

        result.setId(gitProfile.getId());
        result.setLogin(gitProfile.getLogin());
        result.setAvatarUrl(gitProfile.getAvatarUrl());
        result.setGravatarId(gitProfile.getGravatarId());
        result.setUrl(gitProfile.getUrl());
        result.setHtmlUrl(gitProfile.getHtmlUrl());
        result.setGistsUrl(gitProfile.getGistsUrl());
        result.setStarredUrl(gitProfile.getStarredUrl());
        result.setSubscriptionsUrl(gitProfile.getSubscriptionsUrl());
        result.setOrganizationsUrl(gitProfile.getOrganizationsUrl());
        result.setType(gitProfile.getType());
        result.setName(gitProfile.getName());
        result.setCompany(gitProfile.getCompany());
        result.setBlog(gitProfile.getBlog());
        result.setEmail(gitProfile.getEmail());
        result.setHireable(gitProfile.getHireable());
        result.setBio(gitProfile.getBio());
        result.setPublicRepos(gitProfile.getPublicRepos());
        result.setPublicGists(gitProfile.getPublicGists());
        result.setFollowers(gitProfile.getFollowers());
        result.setFollowing(gitProfile.getFollowing());
        result.setCreatedAt(gitProfile.getCreatedAt());
        result.setUpdatedAt(gitProfile.getUpdatedAt());

        return result;
    }

    @Override
    public boolean canPopulate(final Object model) {
        return model instanceof GitProfile;
    }
}
