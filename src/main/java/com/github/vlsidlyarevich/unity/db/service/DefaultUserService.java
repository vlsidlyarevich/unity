package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository repository;

    private final UserSocialService userSocialService;

    private final UserAnalyticsService userAnalyticsService;

    @Autowired
    public DefaultUserService(final UserRepository repository,
                              final UserSocialService userSocialService,
                              final UserAnalyticsService userAnalyticsService) {
        this.repository = repository;
        this.userSocialService = userSocialService;
        this.userAnalyticsService = userAnalyticsService;
    }

    @Override
    public User create(final User user) {
        return Optional.ofNullable(user)
                .map(usr -> {
                    checkForUsernameExistance(user.getUsername());
                    return repository.save(user);
                }).orElseThrow(() -> new IllegalArgumentException("User should not be empty"));
    }

    @Override
    public User find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(final String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUserOrAdmin(#id)")
    public User update(final String id, final User user) {
        return Optional.ofNullable(user)
                .map(usr -> {
                    usr.setId(id);
                    return repository.save(usr);
                }).orElseThrow(() -> new IllegalArgumentException("User should not be empty"));
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUserOrAdmin(#id)")
    public String delete(final String id) {
        repository.delete(id);
        userSocialService.deleteByUserId(id);
        userAnalyticsService.deleteAllReports(id);
        return id;
    }

    private void checkForUsernameExistance(final String username) {
        if (usernameExists(username)) {
            throw new UsernameExistsException("User with username: "
                    + username + " exists");
        }
    }

    private boolean usernameExists(final String username) {
        return repository.findAll()
                .stream()
                .anyMatch(user -> Objects.equals(user.getUsername(), username));
    }
}
