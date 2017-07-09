package com.github.vlsidlyarevich.unity.db.helper;

import com.github.vlsidlyarevich.unity.common.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.db.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserHelper {

    private final UserRepository repository;

    public UserHelper(final UserRepository repository) {
        this.repository = repository;
    }

    public void checkForUserExistance(final String id) {
        if (userExists(id)) {
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

    public boolean userExists(final String id) {
        return Objects.nonNull(repository.findOne(id));
    }
}
