package com.github.vlsidlyarevich.unity.git.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GitRepository {

    private Integer id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("owner")
    private GitRepoOwner owner;
    @JsonProperty("private")
    private Boolean isPrivate;

}

//TODO
//        "private": false,
//        "html_url": "https://github.com/vlsidlyarevich/Angular2-Hero",
//        "description": "Angular 2 based quickstart",
//        "fork": false,
//        "url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero",
//        "forks_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/forks",
//        "keys_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/keys{/key_id}",
//        "collaborators_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/collaborators{/collaborator}",
//        "teams_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/teams",
//        "hooks_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/hooks",
//        "issue_events_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/issues/events{/number}",
//        "events_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/events",
//        "assignees_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/assignees{/user}",
//        "branches_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/branches{/branch}",
//        "tags_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/tags",
//        "blobs_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/git/blobs{/sha}",
//        "git_tags_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/git/tags{/sha}",
//        "git_refs_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/git/refs{/sha}",
//        "trees_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/git/trees{/sha}",
//        "statuses_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/statuses/{sha}",
//        "languages_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/languages",
//        "stargazers_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/stargazers",
//        "contributors_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/contributors",
//        "subscribers_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/subscribers",
//        "subscription_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/subscription",
//        "commits_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/commits{/sha}",
//        "git_commits_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/git/commits{/sha}",
//        "comments_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/comments{/number}",
//        "issue_comment_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/issues/comments{/number}",
//        "contents_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/contents/{+path}",
//        "compare_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/compare/{base}...{head}",
//        "merges_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/merges",
//        "archive_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/{archive_format}{/ref}",
//        "downloads_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/downloads",
//        "issues_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/issues{/number}",
//        "pulls_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/pulls{/number}",
//        "milestones_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/milestones{/number}",
//        "notifications_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/notifications{?since,all,participating}",
//        "labels_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/labels{/name}",
//        "releases_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/releases{/id}",
//        "deployments_url": "https://api.github.com/repos/vlsidlyarevich/Angular2-Hero/deployments",
//        "created_at": "2016-05-27T06:31:59Z",
//        "updated_at": "2017-01-16T11:37:30Z",
//        "pushed_at": "2016-07-22T21:00:57Z",
//        "git_url": "git://github.com/vlsidlyarevich/Angular2-Hero.git",
//        "ssh_url": "git@github.com:vlsidlyarevich/Angular2-Hero.git",
//        "clone_url": "https://github.com/vlsidlyarevich/Angular2-Hero.git",
//        "svn_url": "https://github.com/vlsidlyarevich/Angular2-Hero",
//        "homepage": null,
//        "size": 15365,
//        "stargazers_count": 2,
//        "watchers_count": 2,
//        "language": "TypeScript",
//        "has_issues": true,
//        "has_downloads": true,
//        "has_wiki": true,
//        "has_pages": false,
//        "forks_count": 0,
//        "mirror_url": null,
//        "open_issues_count": 0,
//        "forks": 0,
//        "open_issues": 0,
//        "watchers": 2,
//        "default_branch": "master",
//        "network_count": 0,
//        "subscribers_count": 1
