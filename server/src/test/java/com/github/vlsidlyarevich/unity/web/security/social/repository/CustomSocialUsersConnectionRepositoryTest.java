package com.github.vlsidlyarevich.unity.web.security.social.repository;

import com.github.vlsidlyarevich.unity.domain.model.SocialUserConnection;
import com.github.vlsidlyarevich.unity.domain.repository.SocialUserConnectionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.vlsidlyarevich.unity.TestUtils.createSocialConnection;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class CustomSocialUsersConnectionRepositoryTest {

    @Mock
    private SocialUserConnectionRepository socialUserConnectionRepository;

    @Mock
    private ConnectionFactoryLocator connectionFactoryLocator;

    private CustomSocialUsersConnectionRepository connectionRepository;

    @Before
    public void setUp() {
        this.connectionRepository = new CustomSocialUsersConnectionRepository(socialUserConnectionRepository,
                connectionFactoryLocator);
    }

    @Test
    public void findUserIdsWithConnection_Success_IfUserConnected() {
        final String providerId = "providerId";
        final String providerUserId = "providerUserId";
        final ConnectionKey key = new ConnectionKey(providerId, providerUserId);

        final Connection connection = mock(Connection.class);

        doReturn(key).when(connection).getKey();

        final List<SocialUserConnection> socialUserConnections
                = new ArrayList() {{
            add(createSocialConnection());
        }};

        final String userId = socialUserConnections.get(0).getUserId();

        doReturn(socialUserConnections).when(socialUserConnectionRepository)
                .findAllByProviderIdAndProviderUserId(providerId, providerUserId);

        Assert.assertTrue(connectionRepository.findUserIdsWithConnection(connection).contains(userId));
    }

    @Test
    public void findUserIdsWithConnection_Empty_IfNoConnectedUsers() {
        final String providerId = "providerId";
        final String providerUserId = "providerUserId";
        final ConnectionKey key = new ConnectionKey(providerId, providerUserId);

        final Connection connection = mock(Connection.class);

        doReturn(key).when(connection).getKey();

        doReturn(Collections.emptyList()).when(socialUserConnectionRepository)
                .findAllByProviderIdAndProviderUserId(providerId, providerUserId);

        Assert.assertTrue(connectionRepository.findUserIdsWithConnection(connection).isEmpty());
    }

    @Test
    public void findUserIdsConnectedTo_Success_IfUserConnected() {
        final String providerId = "providerId";
        final Set<String> providerUserIds = new HashSet<>();

        final List<SocialUserConnection> socialUserConnections
                = new ArrayList() {{
            add(createSocialConnection());
        }};

        final String userId = socialUserConnections.get(0).getUserId();

        doReturn(socialUserConnections).when(socialUserConnectionRepository)
                .findAllByProviderIdAndProviderUserIdIn(providerId, providerUserIds);

        Assert.assertTrue(connectionRepository.findUserIdsConnectedTo(providerId, providerUserIds).contains(userId));
    }

    @Test
    public void findUserIdsConnectedTo_Empty_IfNoConnectedUsers() {
        final String providerId = "providerId";
        final Set<String> providerUserIds = new HashSet<>();

        doReturn(Collections.emptyList()).when(socialUserConnectionRepository)
                .findAllByProviderIdAndProviderUserIdIn(providerId, providerUserIds);

        Assert.assertTrue(connectionRepository.findUserIdsConnectedTo(providerId, providerUserIds).isEmpty());
    }

    @Test
    public void createConnectionRepository_Success_IfUserIdPresent() {
        final ConnectionRepository result
                = connectionRepository.createConnectionRepository("user");

        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createConnectionRepository_ExceptionThrown_IfUserIdNull() {
        connectionRepository.createConnectionRepository(null);
    }
}
