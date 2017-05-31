package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        if (usernameExists(user.getUsername())) {
            throw new UsernameExistsException("User with username: "
                    + user.getUsername() + " exists");
        }
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(user);
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
    @PreAuthorize("@securityContextCurrentUserService.canAccessUser(#id)")
    public User update(final String id, final User user) {
        user.setId(id);

        final User saved = repository.findOne(id);

        if (saved != null) {
            user.setCreatedAt(saved.getCreatedAt());
            user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(user);
        return user;
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUser(#id)")
    public String delete(final String id) {
        repository.delete(id);
        userSocialService.deleteByUserId(id);
        userAnalyticsService.deleteAllReports(id);
        return id;
    }

    private boolean usernameExists(final String username) {
        return repository.findAll()
                .stream()
                .anyMatch(user -> Objects.equals(user.getUsername(), username));
    }
}
