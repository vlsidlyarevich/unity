package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.helper.UserHelper;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserSocialService implements UserSocialService {

    private final UserSocialRepository repository;

    private final UserHelper userHelper;

    @Autowired
    public DefaultUserSocialService(final UserSocialRepository repository,
                                    final UserHelper userHelper) {
        this.repository = repository;
        this.userHelper = userHelper;
    }

    @Override
    public UserSocial create(final UserSocial userSocial) {
        return Optional.ofNullable(userSocial)
                .map(repository::save)
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
