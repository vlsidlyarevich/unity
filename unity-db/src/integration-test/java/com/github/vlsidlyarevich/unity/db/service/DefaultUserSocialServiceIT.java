package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseIT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnityDatabaseIT.class)
public class DefaultUserSocialServiceIT {

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
    public void findByUserId_Success_IfPresent() throws Exception {
    }

    @Test
    public void findByUserId_Null_IfNotPresent() throws Exception {
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

    @Test(expected = Exception.class)
    public void update_ExceptionThrown_ifNotPresent() throws Exception {

    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
    }

    @Test
    public void deleteByUserId_Success_IfPresent() throws Exception {
    }
}
