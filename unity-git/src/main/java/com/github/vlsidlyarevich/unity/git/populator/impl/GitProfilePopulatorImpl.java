package com.github.vlsidlyarevich.unity.git.populator.impl;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import com.github.vlsidlyarevich.unity.git.model.GitProfileData;
import com.github.vlsidlyarevich.unity.git.populator.GitProfilePopulator;
import com.github.vlsidlyarevich.unity.git.service.GitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GitProfilePopulatorImpl implements GitProfilePopulator {

    @Autowired
    private GitProfileService gitProfileService;

    public GitProfileData populate(GitProfile gitProfile){
        //TODO
        return new GitProfileData();
    }
}
