package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.assistant.UserAssistant;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserService implements UserService {

    private final UserRepository repository;
    private final UserSocialService userSocialService;
    private final UserAnalyticsService userAnalyticsService;
    private final UserAssistant userHelper;

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
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(final String id, final User user) {
        return Optional.ofNullable(user)
                .map(usr -> {
                    usr.setId(id);
                    return repository.save(usr);
                }).orElseThrow(() -> new IllegalArgumentException("User should not be empty"));
    }

    @Override
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
