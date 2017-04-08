package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.UserProfile;
import com.github.vlsidlyarevich.unity.db.repository.UserProfileRepository;
import com.github.vlsidlyarevich.unity.db.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Override
    public UserProfile create(UserProfile userProfile) {
        userProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(userProfile);
    }

    @Override
    public UserProfile find(String id) {
        return repository.findOne(id);
    }

    @Override
    public UserProfile findByUsername(String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public UserProfile update(String id, UserProfile userProfile) {
        userProfile.setId(id);

        UserProfile saved = repository.findOne(id);

        if (saved != null) {
            userProfile.setCreatedAt(saved.getCreatedAt());
            userProfile.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            userProfile.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(userProfile);
        return userProfile;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(String id) {
        repository.delete(id);
        return id;
    }
    
}
