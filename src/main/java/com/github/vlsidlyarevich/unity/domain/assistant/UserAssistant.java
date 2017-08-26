package com.github.vlsidlyarevich.unity.domain.assistant;

import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserAssistant {

    private final UserRepository repository;

    public UserAssistant(final UserRepository repository) {
        this.repository = repository;
    }

    public void checkForUserExistance(final String id) {
        if (userNotExists(id)) {
            throw new UserNotFoundException(String
                    .format("User with user id: %s not found", id));
        }
    }

    public void checkForUsernameExistance(final String username) {
        if (usernameExists(username)) {
            throw new UsernameExistsException("User with username: "
                    + username + " exists");
        }
    }

    public boolean usernameExists(final String username) {
        return repository.findAll()
                .stream()
                .anyMatch(user -> Objects.equals(user.getUsername(), username));
    }

    public boolean userNotExists(final String id) {
        return Objects.isNull(repository.findOne(id));
    }
}
