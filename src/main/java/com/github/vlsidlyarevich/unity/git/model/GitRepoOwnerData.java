package com.github.vlsidlyarevich.unity.git.model;

import com.github.vlsidlyarevich.unity.common.model.GitResult;
import lombok.Data;

@Data
public class GitRepoOwnerData implements GitResult {

    private static final long serialVersionUID = 6471058629012693454L;

    private Integer id;
    private String login;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private String siteAdmin;
}
