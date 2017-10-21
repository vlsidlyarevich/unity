package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import com.github.vlsidlyarevich.unity.web.security.social.exception.SocialLoginException;
import com.github.vlsidlyarevich.unity.web.security.social.model.SocialProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultSocialUserService implements SocialUserService {

    private final UsersConnectionRepository usersConnectionRepository;
    private final UserRepository userRepository;

    @Override
    public User create(final Connection<?> connection) {
        return Optional.ofNullable(connection)
                .map(socialConnection -> {
                    final UserProfile userProfile = socialConnection.fetchUserProfile();

                    final String providerId = socialConnection.getKey().getProviderId();

                    final User user = getUser(userProfile, providerId);

                    createSocialConnection(user.getUsername(), socialConnection);

                    return user;
                }).orElseThrow(() -> new IllegalArgumentException("Connection cannot be null"));
    }

    @Override
    public void delete(final String login) {
        final ConnectionRepository connectionRepository
                = usersConnectionRepository.createConnectionRepository(login);

        connectionRepository.findAllConnections()
                .keySet()
                .forEach(connectionRepository::removeConnections);
    }

    private User getUser(final UserProfile userProfile, final String providerId) {
        final String username = Optional.ofNullable(userProfile.getUsername())
                .orElse(userProfile.getEmail());

        return Optional.ofNullable(username)
                .map(usrname -> {
                    final User user = userRepository.findByUsername(usrname);
                    final SocialProvider provider = SocialProvider.valueOf(providerId.toUpperCase());

                    if (Objects.nonNull(user) && socialLoginEnabledForUser(user, provider)) {
                        return user;
                    } else {
                        throw new SocialLoginException(String
                                .format("Social login through:%s not enabled for user:%s", provider.toString(),
                                        user.getUsername()));
                    }
                }).orElseThrow(() -> new IllegalArgumentException("Username cannot be null"));
    }

    private void createSocialConnection(final String login, final Connection<?> connection) {
        final ConnectionRepository connectionRepository
                = usersConnectionRepository.createConnectionRepository(login);

        connectionRepository.addConnection(connection);
    }

    private boolean socialLoginEnabledForUser(final User user, final SocialProvider provider) {
        switch (provider) {
            case TWITTER:
                return user.isTwitterLoginEnabled();
            case FACEBOOK:
                return user.isFacebookLoginEnabled();
            case LINKEDIN:
                return user.isLinkedInLoginEnabled();
            default:
                return false;
        }
    }
}
