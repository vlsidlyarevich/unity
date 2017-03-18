package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;

public interface GitProfileService extends GitService {

    GitProfile getGitProfile(String gitProfile);
}
