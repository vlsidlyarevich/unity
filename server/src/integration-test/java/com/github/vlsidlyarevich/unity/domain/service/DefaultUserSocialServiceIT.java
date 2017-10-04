package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import com.github.vlsidlyarevich.unity.domain.model.User;
import com.github.vlsidlyarevich.unity.domain.model.UserSocial;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.exception.UserNotFoundException;
import com.github.vlsidlyarevich.unity.domain.repository.UserSocialRepository;
import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static com.github.vlsidlyarevich.unity.TestUtils.createUserSocial;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserSocialServiceIT {

    private User currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSocialService userSocialService;

    @Autowired
    private UserSocialRepository userSocialRepository;

    @Before
    public void setUp() {
        userSocialRepository.deleteAll();

        currentUser = createUser();
        currentUser.addAuthority(Authority.ROLE_ADMIN);

        userService.create(currentUser);

        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(currentUser));
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

    @Test(expected = ResourceNotFoundException.class)
    public void find_ExceptionThrown_IfNotPresent() throws Exception {
        Assert.assertThat(userSocialService.find("id"), nullValue());
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);

        Assert.assertThat(userSocialService.findByUserId(userSocial.getUserId()), is(userSocial));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByUserId_ExceptionThrown_IfNotPresent() throws Exception {
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
        userSocial.setUserId(currentUser.getId());

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

    @Test(expected = UserNotFoundException.class)
    public void update_ExceptionThrown_ifUserNotPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.create(userSocial);
        userSocial.setFirstName("new name");

        userSocialService.update("this id does not exist", userSocial);
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
