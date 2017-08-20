package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.assistant.UserAssistant;
import com.github.vlsidlyarevich.unity.domain.repository.UserSocialRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultUserSocialService implements UserSocialService {

    private final UserSocialRepository repository;

    private final UserAssistant userHelper;

    @Override
    public UserSocial create(final UserSocial userSocial) {
        return Optional.ofNullable(userSocial)
                .map(repository::save)
                .orElseThrow(() ->
                        new IllegalArgumentException("User social data must not be empty"));
    }

    @Override
    public UserSocial find(final String id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("User Social with id:%s not found", id)));
    }

    @Override
    public UserSocial findByUserId(final String userId) {
        return Optional.ofNullable(repository.findByUserId(userId))
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("User Social with user id:%s not found", userId)));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserSocial> findAll() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUserOrAdmin(#id)")
    public UserSocial update(final String id, final UserSocial userSocial) {
        userHelper.checkForUserExistance(id);

        return Optional.ofNullable(userSocial)
                .map(usrSocial -> {
                    usrSocial.setUserId(id);
                    return repository.save(usrSocial);
                }).orElseThrow(()
                        -> new IllegalArgumentException("User social data must not be empty"));
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUserOrAdmin(#id)")
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUserOrAdmin(#userId)")
    public String deleteByUserId(final String userId) {
        repository.deleteByUserId(userId);
        return userId;
    }
}
