package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserSocialService implements UserSocialService {

    private final UserSocialRepository repository;

    @Autowired
    public DefaultUserSocialService(final UserSocialRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserSocial create(final UserSocial userSocial) {
        return Optional.ofNullable(userSocial)
                .map(usrSocial -> {
                    usrSocial.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    return repository.save(usrSocial);
                })
                .orElseThrow(() ->
                        new IllegalArgumentException("User social data must not be empty"));
    }

    @Override
    public UserSocial find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public UserSocial findByUserId(final String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserSocial> findAll() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUser(#id)")
    public UserSocial update(final String id, final UserSocial userSocial) {
        return Optional.ofNullable(userSocial)
                .map(usrSocial -> {
                    usrSocial.setUserId(id);

                    UserSocial saved = repository.findByUserId(userSocial.getUserId());

                    if (saved != null) {
                        userSocial.setId(saved.getId());
                        userSocial.setCreatedAt(saved.getCreatedAt());
                        userSocial.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    } else {
                        userSocial.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    }

                    return repository.save(usrSocial);
                }).orElseThrow(()
                        -> new IllegalArgumentException("User social data must not be empty"));
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUser(#id)")
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }

    @Override
    @PreAuthorize("@securityContextCurrentUserService.canAccessUser(#userId)")
    public String deleteByUserId(final String userId) {
        repository.deleteByUserId(userId);
        return userId;
    }
}
