package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.GitResult;
import com.github.vlsidlyarevich.unity.git.model.GitRepositoryData;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GitRepositoryLanguagesTotalCalculator implements GitDataTotalCalculator {

    @Override
    public Map<String, Integer> calculateTotal(final Collection<? extends GitResult>
                                                       gitResultCollection) {
        final Map<String, Integer> languagesTotal = new HashMap<>();
        List<GitRepositoryData> gitRepositoryDataList
                = (List<GitRepositoryData>) gitResultCollection;

        gitRepositoryDataList
                .stream()
                .map(GitRepositoryData::getLanguages)
                .forEach(languagesList -> languagesList.forEach((languageName, languageCounter) -> {
                    languagesTotal.computeIfPresent(languageName,
                            (key, value) -> value + Integer.valueOf(languageCounter));
                    languagesTotal.putIfAbsent(languageName, Integer.valueOf(languageCounter));
                }));

        return languagesTotal;
    }
}
