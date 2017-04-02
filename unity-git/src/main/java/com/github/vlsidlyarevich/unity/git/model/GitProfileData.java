package com.github.vlsidlyarevich.unity.git.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class GitProfileData {

    private Integer id;
    private String login;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private List<GitRepositoryData> repos;
    private String type;
    private String name;
    private String company;
    private String blog;
    private String email;
    private String hireable;
    private String bio;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    private Date createdAt;
    private Date updatedAt;
}
