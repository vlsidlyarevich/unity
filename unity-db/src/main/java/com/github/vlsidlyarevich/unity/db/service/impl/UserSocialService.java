package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import com.github.vlsidlyarevich.unity.db.service.UserSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserSocialService implements UserSocialService {

    @Autowired
    private UserSocialRepository repository;

    @Override
    public UserSocial create(UserSocial userSocial) {
        userSocial.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(userSocial);
    }

    @Override
    public UserSocial find(String id) {
        return repository.findOne(id);
    }

    @Override
    public UserSocial findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserSocial> findAll() {
        return repository.findAll();
    }

    @Override
    public UserSocial update(String id, UserSocial userSocial) {
        userSocial.setId(id);

        UserSocial saved = repository.findOne(id);

        if (saved != null) {
            userSocial.setCreatedAt(saved.getCreatedAt());
            userSocial.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            userSocial.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(userSocial);
        return userSocial;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(String id) {
        repository.delete(id);
        return id;
    }
    
}
