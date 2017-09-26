package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TwitterProfileDataTagsCalculator implements TwitterProfileDataCalculator {

    @Override
    public Map<String, Integer> calculateTotal(Collection<? extends TwitterResult> popularProfiles) {
        final Map<String, Integer> tagsTotal = new HashMap<>();

        final List<TwitterPopularProfile> twitterPopularProfiles
                = (List<TwitterPopularProfile>) popularProfiles;

        twitterPopularProfiles
                .stream()
                .map(TwitterPopularProfile::getTags)
                .forEach(languagesList -> languagesList.forEach((tagName) -> {
                    tagsTotal.computeIfPresent(tagName,
                            (key, value) -> value + 1);
                    tagsTotal.putIfAbsent(tagName, 1);
                }));

        return tagsTotal;
    }
}
