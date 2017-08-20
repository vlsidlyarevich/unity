package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.common.helper.RandomHelper;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.web.security.social.model.SocialMediaService;
import lombok.AllArgsConstructor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultSocialUserService implements SocialUserService {

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserRepository userRepository;

    @Override
    public User create(final Connection<?> connection) {
        return Optional.ofNullable(connection)
                .map(socialConnection -> {
                    final UserProfile userProfile = socialConnection.fetchUserProfile();

                    final String providerId = socialConnection.getKey().getProviderId();

                    final User user = createUserIfNotExist(userProfile, providerId);

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


    private User createUserIfNotExist(final UserProfile userProfile, final String providerId) {
        final String username = userProfile.getUsername();

        return Optional.ofNullable(username)
                .map(usrname -> {
                    final User user = userRepository.findByUsername(usrname);

                    if (Objects.nonNull(user)) {
                        return user;
                    }

                    final List<Authority> authorities = new ArrayList<>();
                    authorities.add(Authority.ROLE_USER);

                    final User newUser = User.builder()
                            .username(usrname)
                            .password(RandomHelper.randomAlphaNumeric())
                            .authorities(authorities)
                            .socialSignInProvider(SocialMediaService.valueOf(providerId.toUpperCase()))
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .isEnabled(true)
                            .build();

                    return userRepository.save(newUser);
                }).orElseThrow(() -> new IllegalArgumentException("Username cannot be null"));
    }

    private void createSocialConnection(final String login, final Connection<?> connection) {
        final ConnectionRepository connectionRepository
                = usersConnectionRepository.createConnectionRepository(login);

        connectionRepository.addConnection(connection);
    }
}
