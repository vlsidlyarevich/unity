package com.github.vlsidlyarevich.unity.web.security.social.repository;

import com.github.vlsidlyarevich.unity.web.security.social.domain.SocialUserConnection;
import lombok.AllArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CustomSocialUsersConnectionRepository implements UsersConnectionRepository {

    private SocialUserConnectionRepository socialUserConnectionRepository;

    private ConnectionFactoryLocator connectionFactoryLocator;

    @Override
    public List<String> findUserIdsWithConnection(final Connection<?> connection) {
        final ConnectionKey key = connection.getKey();
        final List<SocialUserConnection> socialUserConnections = socialUserConnectionRepository
                .findAllByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId());

        return socialUserConnections.
                stream()
                .map(SocialUserConnection::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> findUserIdsConnectedTo(final String providerId, final Set<String> providerUserIds) {
        final List<SocialUserConnection> socialUserConnections =
                socialUserConnectionRepository.findAllByProviderIdAndProviderUserIdIn(providerId, providerUserIds);

        return socialUserConnections
                .stream()
                .map(SocialUserConnection::getUserId)
                .collect(Collectors.toSet());
    }

    @Override
    public ConnectionRepository createConnectionRepository(final String userId) {
        return Optional.ofNullable(userId)
                .map(id -> new CustomSocialConnectionRepository(id,
                        socialUserConnectionRepository,
                        connectionFactoryLocator)).orElseThrow(()
                        -> new IllegalArgumentException("userId cannot be null"));
    }
}
