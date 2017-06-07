package com.github.vlsidlyarevich.unity.db.repository.integrational;

import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
public class UserRepositoryIntegrationalTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void findByUsernameTest() throws Exception {
        User user = createUser();

        userRepository.save(user);

        User savedUser = userRepository.findByUsername(user.getUsername());

        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals(savedUser.getUsername(), savedUser.getUsername());
        Assert.assertEquals(savedUser.getPassword(), savedUser.getPassword());
        Assert.assertEquals(savedUser.getAuthorities(), savedUser.getAuthorities());
    }

    private User createUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return new User(authorities, TestUtils.getRandomString(8), TestUtils.getRandomString(8),
                false, false, false,
                true);
    }
}
