package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;

import java.util.Optional;

public interface GitProfileService {

    Optional<GitProfile> getGitProfile(String gitProfile);
}
