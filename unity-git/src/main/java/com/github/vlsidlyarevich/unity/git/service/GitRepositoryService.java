package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;

import java.util.Optional;


public interface GitRepositoryService extends GitService {

    Optional<GitRepository[]> getGitRepositories(String gitProfile);

    Optional<GitRepository> getGitRepository(String gitProfile, String repo);
}
