package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.GitResult;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GitRepositoryTopicsTotalCalculator implements GitDataTotalCalculator {

    @Override
    public Map<String, Integer> calculateTotal(final Collection<? extends GitResult>
                                                       gitResultCollection) {
        final Map<String, Integer> topicsTotal = new HashMap<>();
        List<GitRepositoryData> gitRepositoryDataList
                = (List<GitRepositoryData>) gitResultCollection;

        gitRepositoryDataList
                .stream()
                .map(GitRepositoryData::getTopics)
                .forEach(languagesList -> languagesList.forEach((topicName) -> {
                    topicsTotal.computeIfPresent(topicName, (key, value) -> value + 1);
                    topicsTotal.putIfAbsent(topicName, 1);
                }));

        return topicsTotal;
    }
}
