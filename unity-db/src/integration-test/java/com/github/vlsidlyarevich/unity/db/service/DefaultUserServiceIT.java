package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseIT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseIT.class)
public class DefaultUserServiceIT {

    @Test
    public void create() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void findByUsername() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}
