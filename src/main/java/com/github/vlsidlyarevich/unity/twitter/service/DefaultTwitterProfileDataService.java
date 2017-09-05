package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.twitter.model.TwitterProfileData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTwitterProfileDataService implements TwitterProfileDataService {

    private final static Long CURSOR = -1L;

    private final Twitter twitter;

    @Override
    public Optional<TwitterProfileData> getTwitterProfileData(final String username) {
        return Optional.ofNullable(username)
                .map(usrname -> {
                    final TwitterProfileData profileData = new TwitterProfileData();
                    profileData.setSubscriptions(subscriptions(usrname).collect(Collectors.toList()));

                    return Optional.of(profileData);
                }).orElseThrow(() -> new IllegalArgumentException("Twitter username should not be empty"));
    }

    private Stream<User> subscriptions(final String username) {
        try {
            final IDs subscriptionsIDs = twitter.getFriendsIDs(username, CURSOR);

            return twitter.lookupUsers(subscriptionsIDs.getIDs()).stream();
        } catch (final TwitterException ignored) {
            return Stream.empty();
        }
    }
}
