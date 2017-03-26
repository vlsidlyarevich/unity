package com.github.vlsidlyarevich.unity.git.aggregator;

import com.github.vlsidlyarevich.unity.git.populator.GitPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GitDataAggregator {

    @Autowired
    private List<GitPopulator> gitPopulators;

//    public GitProfileData getGitProfileData(String gitLogin) {
//
//    }
//
//    public boolean checkIfExists(String gitLogin) {
//
//    }
//
//    private GitProfileData aggregateData(String gitLogin) {
//
//    }
}
