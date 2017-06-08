package com.github.vlsidlyarevich.unity.db.repository.integration;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserSocial;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
public class UserSocialRepositoryIntegrationTest {

    @Autowired
    private UserSocialRepository userSocialRepository;

    @After
    public void cleanUp() {
        userSocialRepository.deleteAll();
    }

    @Test
    public void findByUserIdTest() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialRepository.save(userSocial);

        UserSocial savedUserSocial = userSocialRepository.findByUserId(userSocial.getUserId());

        Assert.assertNotNull(savedUserSocial);
        Assert.assertNotNull(savedUserSocial.getId());
        Assert.assertEquals(userSocial.getUserId(), savedUserSocial.getUserId());
    }

    @Test
    public void deleteByUserIdTest() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialRepository.save(userSocial);

        userSocialRepository.deleteByUserId(userSocial.getUserId());

        Assert.assertNull(userSocialRepository.findByUserId(userSocial.getUserId()));
        Assert.assertEquals(0, userSocialRepository.findAll().size());
    }
}