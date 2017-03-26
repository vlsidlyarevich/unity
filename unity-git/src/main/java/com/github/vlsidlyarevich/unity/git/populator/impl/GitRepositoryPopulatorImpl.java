package com.github.vlsidlyarevich.unity.git.populator.impl;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import com.github.vlsidlyarevich.unity.git.populator.GitRepositoryPopulator;
import org.springframework.stereotype.Component;

@Component
public class GitRepositoryPopulatorImpl implements GitRepositoryPopulator {

    public GitRepositoryData populate(GitRepository gitRepository) {
        //TODO
        return new GitRepositoryData();
    }

    @Override
    public boolean canPopulate(Object model) {
        return model instanceof GitRepository;
    }
}
