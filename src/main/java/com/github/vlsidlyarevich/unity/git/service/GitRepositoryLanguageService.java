package com.github.vlsidlyarevich.unity.git.service;

import java.util.Map;
import java.util.Optional;

public interface GitRepositoryLanguageService extends GitService {

    Optional<Map<String, String>> getGitRepoLanguages(String url);
}
