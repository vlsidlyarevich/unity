package com.github.vlsidlyarevich.unity.git.model;

import com.github.vlsidlyarevich.unity.common.model.GitResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class GitProfileData extends GitResult {

    private static final long serialVersionUID = 796518780368910481L;

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
    private Map<String, Integer> languagesTotal;
    private Map<String, Integer> topicsTotal;
    private Map<String, Integer> forksLanguagesTotal;
    private Map<String, Integer> forksTopicsTotal;
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
