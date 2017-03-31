package com.github.vlsidlyarevich.unity.git.populator.impl;

import com.github.vlsidlyarevich.unity.git.model.GitRepoOwner;
import com.github.vlsidlyarevich.unity.git.model.GitRepoOwnerData;
import com.github.vlsidlyarevich.unity.git.populator.GitRepoOwnerPopulator;
import org.springframework.stereotype.Component;

@Component
public class GitRepoOwnerPopulatorImpl implements GitRepoOwnerPopulator {

    @Override
    public GitRepoOwnerData populate(GitRepoOwner model) {
        GitRepoOwnerData result = new GitRepoOwnerData();

        result.setId(model.getId());
        result.setLogin(model.getLogin());
        result.setAvatarUrl(model.getAvatarUrl());
        result.setGravatarId(model.getGravatarId());
        result.setUrl(model.getUrl());
        result.setHtmlUrl(model.getHtmlUrl());
        result.setFollowersUrl(model.getFollowersUrl());
        result.setFollowingUrl(model.getFollowingUrl());
        result.setGistsUrl(model.getGistsUrl());
        result.setStarredUrl(model.getStarredUrl());
        result.setSubscriptionsUrl(model.getSubscriptionsUrl());
        result.setOrganizationsUrl(model.getOrganizationsUrl());
        result.setReposUrl(model.getReposUrl());
        result.setEventsUrl(model.getEventsUrl());
        result.setReceivedEventsUrl(model.getReceivedEventsUrl());
        result.setType(model.getType());
        result.setSiteAdmin(model.getSiteAdmin());

        return result;
    }

    @Override
    public boolean canPopulate(Object model) {
        return model instanceof GitRepoOwner;
    }
}
