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

        //TODO



        return result;
    }

//    private Integer id;
//    private String login;
//    private String avatarUrl;
//    private String gravatarId;
//    private String url;
//    private String htmlUrl;
//    private String followersUrl;
//    private String followingUrl;
//    private String gistsUrl;
//    private String starredUrl;
//    private String subscriptionsUrl;
//    private String organizationsUrl;
//    private String reposUrl;
//    private String eventsUrl;
//    private String receivedEventsUrl;
//    private String type;
//    private String siteAdmin;

    @Override
    public boolean canPopulate(Object model) {
        return model instanceof GitRepoOwner;
    }
}
