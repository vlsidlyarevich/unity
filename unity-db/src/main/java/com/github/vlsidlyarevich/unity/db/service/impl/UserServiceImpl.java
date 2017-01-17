package com.github.vlsidlyarevich.unity.db.service.impl;

import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User find(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(String id, User object) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
