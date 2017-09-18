package com.github.vlsidlyarevich.unity.domain.assistant;

import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.exception.UsernameExistsException;
import com.github.vlsidlyarevich.unity.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserAssistant implements UserAssistant {

    private final UserRepository repository;

    @Override
    public void checkForUserExistance(final String id) {
        if (userNotExists(id)) {
            throw new UserNotFoundException(String
                    .format("User with user id: %s not found", id));
        }
    }

    @Override
    public void checkForUsernameExistance(final String username) {
        if (usernameExists(username)) {
            throw new UsernameExistsException(String.format("User with username: %s exists", username));
        }
    }

    @Override
    public boolean usernameExists(final String username) {
        return repository.findAll()
                .stream()
                .anyMatch(user -> Objects.equals(user.getUsername(), username));
    }

    @Override
    public boolean userNotExists(final String id) {
        return Objects.isNull(repository.findOne(id));
    }
}
