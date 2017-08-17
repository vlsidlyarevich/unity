package com.github.vlsidlyarevich.unity.web.security.social.service;

import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class DefaultSocialUserService implements SocialUserService {

    private final Logger log = LoggerFactory.getLogger(DefaultSocialUserService.class);

    private final UsersConnectionRepository usersConnectionRepository;

    private final UserRepository userRepository;

    @Override
    public User create(final Connection<?> connection, final String language) {

        if (connection == null) {
            log.error("Cannot create social user because connection is null");
            throw new IllegalArgumentException("Connection cannot be null");
        }

        UserProfile userProfile = connection.fetchUserProfile();
        String providerId = connection.getKey().getProviderId();
        String imageUrl = connection.getImageUrl();
        User user = createUserIfNotExist(userProfile, langKey, providerId, imageUrl);
        createSocialConnection(user.getUsername(), connection);
//        mailService.sendSocialRegistrationValidationEmail(user, providerId);
    }

    @Override
    public String delete(final String login) {
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(login);

        connectionRepository.findAllConnections().keySet().stream()
                .forEach(providerId -> {
                    connectionRepository.removeConnections(providerId);
                    log.debug("Delete user social connection providerId: {}", providerId);
                });
    }


    private User createUserIfNotExist(final UserProfile userProfile, final String language, final String providerId,
                                      final String imageUrl) {
        String email = userProfile.getEmail();
        String userName = userProfile.getUsername();

        if (!StringUtils.isBlank(userName)) {
            userName = userName.toLowerCase(Locale.ENGLISH);
        }

        if (StringUtils.isBlank(email) && StringUtils.isBlank(userName)) {
            log.error("Cannot create social user because email and login are null");
            throw new IllegalArgumentException("Email and login cannot be null");
        }
//        if (StringUtils.isBlank(email) && userRepository.findByUsername(userName).isPresent()) {
//            log.error("Cannot create social user because email is null and login already exist, login -> {}", userName);
//            throw new IllegalArgumentException("Email cannot be null with an existing login");
//        }
//        if (!StringUtils.isBlank(email)) {
//            Optional<User> user = userRepository.findOne(email);
//            if (user.isPresent()) {
//                log.info("User already exist associate the connection to this account");
//                return user.get();
//            }
//        }

        String login = getLoginDependingOnProviderId(userProfile, providerId);
        String encryptedPassword = "123";
//        Set<Authority> authorities = new HashSet<>(1);
//        authorities.add(authorityRepository.findOne("ROLE_USER"));

        User newUser = new User();
        newUser.setUsername("username");
//        newUser.setLogin(login);
//        newUser.setPassword(encryptedPassword);
//        newUser.setFirstName(userProfile.getFirstName());
//        newUser.setLastName(userProfile.getLastName());
//        newUser.setEmail(email);
//        newUser.setActivated(true);
//        newUser.setAuthorities(authorities);
//        newUser.setLangKey(langKey);
//        newUser.setImageUrl(imageUrl);

        return userRepository.save(newUser);
    }

    /**
     * @return login if provider manage a login like Twitter or GitHub otherwise email address.
     * Because provider like Google or Facebook didn't provide login or login like "12099388847393"
     */
    private String getLoginDependingOnProviderId(UserProfile userProfile, String providerId) {
        switch (providerId) {
            case "twitter":
                return userProfile.getUsername().toLowerCase();
            default:
                return userProfile.getEmail();
        }
    }

    private void createSocialConnection(final String login, final Connection<?> connection) {
        final ConnectionRepository connectionRepository
                = usersConnectionRepository.createConnectionRepository(login);

        connectionRepository.addConnection(connection);
    }
}
