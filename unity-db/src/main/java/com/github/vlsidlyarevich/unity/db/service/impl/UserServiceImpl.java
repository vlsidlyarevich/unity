package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(String id) {
        repository.delete(id);
        return id;
    }
}
