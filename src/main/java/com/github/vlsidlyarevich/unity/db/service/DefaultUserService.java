package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.helper.UserHelper;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository repository;

    private final UserSocialService userSocialService;

    private final UserAnalyticsService userAnalyticsService;

    private final UserHelper userHelper;

    @Autowired
    public DefaultUserService(final UserRepository repository,
                              final UserSocialService userSocialService,
                              final UserAnalyticsService userAnalyticsService,
                              final UserHelper userHelper) {
        this.repository = repository;
        this.userSocialService = userSocialService;
        this.userAnalyticsService = userAnalyticsService;
        this.userHelper = userHelper;
    }

    @Override
    public User create(final User user) {
        return Optional.ofNullable(user)
                .map(usr -> {
                    userHelper.checkForUsernameExistance(user.getUsername());
                    return repository.save(user);
                }).orElseThrow(() -> new IllegalArgumentException("User should not be empty"));
    }

    @Override
    public User find(final String id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with id:%s not found", id)));
    }

    @Override
    public User findByUsername(final String userName) {
        return Optional.ofNullable(repository.findByUsername(userName))
                .orElseThrow(() ->
                        new UserNotFoundException(String
                                .format("User with username:%s not found", userName)));
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

        try {
            userSocialService.deleteByUserId(id);
            userAnalyticsService.deleteAllReports(id);
        } catch (ResourceNotFoundException ignored) {

        }

        return id;
    }
}
