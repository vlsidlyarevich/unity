package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserAnalyticsService;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimpleUserService implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserSocialService userSocialService;

    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @Override
    public User create(User user) {
        if (usernameExists(user.getUsername())) {
            throw new UsernameExistsException("User with username: " + user.getUsername() + " exists");
        }
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(user);
    }

    @Override
    public User find(String id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(#id)")
    public User update(String id, User user) {
        user.setId(id);

        User saved = repository.findOne(id);

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
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(#id)")
    public String delete(String id) {
        repository.delete(id);
        userSocialService.delete(id);
        userAnalyticsService.delete(id);
        return id;
    }

    private boolean usernameExists(String username) {
        return repository.findAll()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }
}
