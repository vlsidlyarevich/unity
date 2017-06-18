package com.github.vlsidlyarevich.unity.git.service;


import com.github.vlsidlyarevich.unity.git.model.GitProfileData;

import java.util.Optional;

public interface GitProfileDataService extends GitService {

    Optional<GitProfileData> getGitProfileData(String gitLogin);
}
