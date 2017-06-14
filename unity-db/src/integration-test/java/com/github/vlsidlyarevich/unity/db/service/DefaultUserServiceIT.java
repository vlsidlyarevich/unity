package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseIT;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnityDatabaseIT.class)
public class DefaultUserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }


    @Test
    public void create_Success_IfValid() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void create_ExceptionThrown_ifNull() throws Exception {

    }

    @Test
    public void find_Success_IfPresent() throws Exception {
    }

    @Test
    public void find_Null_IfNotPresent() throws Exception {
    }

    @Test
    public void findByUsername_Success_IfPresent() throws Exception {
    }

    @Test
    public void findByUsername_Null_IfNotPresent() throws Exception {
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
    }

    @Test
    public void update_Success_IfPresent() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNull() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNotPresent() throws Exception {

    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
    }
}
