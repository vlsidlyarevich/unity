package com.github.vlsidlyarevich.unity.web.security.social.repository;

import com.github.vlsidlyarevich.unity.domain.model.SocialUserConnection;
import com.github.vlsidlyarevich.unity.domain.repository.SocialUserConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NoSuchConnectionException;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomSocialConnectionRepository implements ConnectionRepository {

    private final String userId;

    private final SocialUserConnectionRepository socialUserConnectionRepository;

    private final ConnectionFactoryLocator connectionFactoryLocator;

    @Override
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        final List<SocialUserConnection> socialUserConnections
                = socialUserConnectionRepository.findAllByUserIdOrderByProviderIdAscRankAsc(userId);

        final List<Connection<?>> connections = socialUserConnectionsToConnections(socialUserConnections);

        final MultiValueMap<String, Connection<?>> connectionsByProviderId = new LinkedMultiValueMap<>();

        final Set<String> registeredProviderIds = connectionFactoryLocator.registeredProviderIds();

        registeredProviderIds.forEach(registeredProviderId -> connectionsByProviderId
                .put(registeredProviderId, Collections.emptyList()));

        connections.forEach(connection -> {
            final String providerId = connection.getKey().getProviderId();

            if (connectionsByProviderId.get(providerId).isEmpty()) {
                connectionsByProviderId.put(providerId, new LinkedList<>());
            }
            connectionsByProviderId.add(providerId, connection);
        });

        return connectionsByProviderId;
    }

    @Override
    public List<Connection<?>> findConnections(final String providerId) {
        final List<SocialUserConnection> socialUserConnections
                = socialUserConnectionRepository.findAllByUserIdAndProviderIdOrderByRankAsc(userId, providerId);

        return socialUserConnectionsToConnections(socialUserConnections);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A> List<Connection<A>> findConnections(final Class<A> apiType) {
        final List<?> connections = findConnections(getProviderId(apiType));

        return (List<Connection<A>>) connections;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
            final MultiValueMap<String, String> providerUserIdsByProviderId) {

        if (Objects.isNull(providerUserIdsByProviderId) || providerUserIdsByProviderId.isEmpty()) {
            throw new IllegalArgumentException("Unable to execute find: no providerUsers provided");
        }

        final MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<>();

        providerUserIdsByProviderId.forEach((providerId, providerUserIds) -> {
            final List<Connection<?>> connections = providerUserIdsToConnections(providerId, providerUserIds);
            connections.forEach(connection -> connectionsForUsers.add(providerId, connection));
        });

        return connectionsForUsers;
    }

    @Override
    public Connection<?> getConnection(final ConnectionKey connectionKey) {
        final SocialUserConnection socialUserConnection
                = socialUserConnectionRepository.findOneByUserIdAndProviderIdAndProviderUserId(userId,
                connectionKey.getProviderId(), connectionKey.getProviderUserId());

        return Optional.ofNullable(socialUserConnection)
                .map(this::socialUserConnectionToConnection)
                .orElseThrow(() -> new NoSuchConnectionException(connectionKey));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A> Connection<A> getConnection(final Class<A> apiType, final String providerUserId) {
        final String providerId = getProviderId(apiType);

        return (Connection<A>) getConnection(new ConnectionKey(providerId, providerUserId));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A> Connection<A> getPrimaryConnection(final Class<A> apiType) {
        final String providerId = getProviderId(apiType);

        final Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);

        return Optional.ofNullable(connection)
                .orElseThrow(() -> new NotConnectedException(providerId));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A> Connection<A> findPrimaryConnection(final Class<A> apiType) {
        final String providerId = getProviderId(apiType);

        return (Connection<A>) findPrimaryConnection(providerId);
    }

    @Override
    public void addConnection(final Connection<?> connection) {
        final Long rank = getNewMaxRank(connection.getKey().getProviderId()).longValue();

        final SocialUserConnection socialUserConnectionToSave = connectionToUserSocialConnection(connection, rank);

        socialUserConnectionRepository.save(socialUserConnectionToSave);
    }

    @Override
    public void updateConnection(final Connection<?> connection) {
        final SocialUserConnection socialUserConnection =
                socialUserConnectionRepository.findOneByUserIdAndProviderIdAndProviderUserId(userId,
                        connection.getKey().getProviderId(), connection.getKey().getProviderUserId());

        if (socialUserConnection != null) {
            final SocialUserConnection socialUserConnectionToUdpate
                    = connectionToUserSocialConnection(connection, socialUserConnection.getRank());

            socialUserConnectionToUdpate.setId(socialUserConnection.getId());

            socialUserConnectionRepository.save(socialUserConnectionToUdpate);
        }
    }

    @Override
    public void removeConnections(final String providerId) {
        socialUserConnectionRepository.deleteByUserIdAndProviderId(userId, providerId);
    }

    @Override
    public void removeConnection(final ConnectionKey connectionKey) {
        socialUserConnectionRepository.deleteByUserIdAndProviderIdAndProviderUserId(userId,
                connectionKey.getProviderId(), connectionKey.getProviderUserId());
    }

    private Double getNewMaxRank(final String providerId) {
        final List<SocialUserConnection> socialUserConnections = socialUserConnectionRepository
                .findAllByUserIdAndProviderIdOrderByRankAsc(userId, providerId);

        return socialUserConnections
                .stream()
                .mapToDouble(SocialUserConnection::getRank)
                .max()
                .orElse(0D) + 1D;
    }

    private Connection<?> findPrimaryConnection(final String providerId) {
        final List<SocialUserConnection> socialUserConnections = socialUserConnectionRepository
                .findAllByUserIdAndProviderIdOrderByRankAsc(userId, providerId);

        return !socialUserConnections.isEmpty()
                ? socialUserConnectionToConnection(socialUserConnections.get(0))
                : null;
    }

    private SocialUserConnection connectionToUserSocialConnection(final Connection<?> connection,
                                                                  final Long rank) {
        final ConnectionData connectionData = connection.createData();

        return SocialUserConnection.builder()
                .id(userId)
                .providerId(connection.getKey().getProviderId())
                .providerUserId(connection.getKey().getProviderUserId())
                .rank(rank)
                .displayName(connection.getDisplayName())
                .profileURL(connection.getProfileUrl())
                .imageURL(connection.getImageUrl())
                .accessToken(connectionData.getAccessToken())
                .secret(connectionData.getSecret())
                .refreshToken(connectionData.getRefreshToken())
                .expireTime(connectionData.getExpireTime())
                .build();
    }

    private List<Connection<?>> providerUserIdsToConnections(final String providerId,
                                                             final List<String> providerUserIds) {
        final List<SocialUserConnection> socialUserConnections = socialUserConnectionRepository
                .findAllByUserIdAndProviderIdAndProviderUserIdIn(userId, providerId, providerUserIds);

        return socialUserConnectionsToConnections(socialUserConnections);
    }

    private List<Connection<?>> socialUserConnectionsToConnections(
            final List<SocialUserConnection> socialUserConnections) {

        return socialUserConnections
                .stream()
                .map(this::socialUserConnectionToConnection)
                .collect(Collectors.toList());
    }

    private Connection<?> socialUserConnectionToConnection(final SocialUserConnection socialUserConnection) {
        final ConnectionData connectionData = new ConnectionData(socialUserConnection.getProviderId(),
                socialUserConnection.getProviderUserId(),
                socialUserConnection.getDisplayName(),
                socialUserConnection.getProfileURL(),
                socialUserConnection.getImageURL(),
                socialUserConnection.getAccessToken(),
                socialUserConnection.getSecret(),
                socialUserConnection.getRefreshToken(),
                socialUserConnection.getExpireTime());

        final ConnectionFactory<?> connectionFactory
                = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());

        return connectionFactory.createConnection(connectionData);
    }

    private <A> String getProviderId(final Class<A> apiType) {
        return connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
    }
}
