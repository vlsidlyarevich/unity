package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseIT;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserSocial;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnityDatabaseIT.class)
public class DefaultUserSocialServiceIT {

    @Autowired
    private UserSocialService userSocialService;

    @Autowired
    private UserSocialRepository userSocialRepository;

    @Before
    public void setUp() {
        userSocialRepository.deleteAll();
    }

    @After
    public void cleanUp() {
        userSocialRepository.deleteAll();
    }

    @Test
    public void create_Success_IfValid() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);

        Assert.assertThat(userSocialService.findAll().size(), is(1));
        Assert.assertThat(userSocialService.find(userSocial.getId()), is(userSocial));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_ExceptionThrown_ifNull() throws Exception {
        userSocialService.create(null);
    }

    @Test
    public void find_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);

        Assert.assertThat(userSocialService.findAll().size(), is(1));
        Assert.assertThat(userSocialService.find(userSocial.getId()), notNullValue());
    }

    @Test
    public void find_Null_IfNotPresent() throws Exception {
        Assert.assertThat(userSocialService.find("id"), nullValue());
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);

        Assert.assertThat(userSocialService.findByUserId(userSocial.getUserId()), is(userSocial));
    }

    @Test
    public void findByUserId_Null_IfNotPresent() throws Exception {
        Assert.assertThat(userSocialService.findByUserId("userid"), nullValue());
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);

        Assert.assertThat(userSocialService.findAll().size(), is(1));
        Assert.assertTrue(userSocialService.findAll().contains(userSocial));
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        Assert.assertTrue(userSocialService.findAll().isEmpty());
    }

    @Test
    public void update_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocial.setFirstName("new name");

        userSocialService.update(userSocial.getUserId(), userSocial);

        Assert.assertThat(userSocialService.find(userSocial.getId()).getFirstName(), is(userSocial.getFirstName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNull() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocial.setFirstName("new name");

        userSocialService.update(userSocial.getUserId(), null);
    }

    @Test
    public void update_NewCreated_ifNotPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocial.setFirstName("new name");

        userSocialService.update("this id does not exist", userSocial);

        Assert.assertFalse(userSocialService.findAll().isEmpty());
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocialService.delete(userSocial.getId());

        Assert.assertTrue(userSocialService.findAll().isEmpty());
    }

    @Test
    public void deleteByUserId_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocialService.deleteByUserId(userSocial.getUserId());

        Assert.assertTrue(userSocialService.findAll().isEmpty());
    }
}
