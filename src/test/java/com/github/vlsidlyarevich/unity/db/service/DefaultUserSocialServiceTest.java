package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.helper.UserHelper;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserSocial;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DefaultUserSocialServiceTest {

    private UserSocialService userSocialService;

    @Mock
    private UserSocialRepository userSocialRepository;

    @Mock
    private UserHelper userHelper;

    @Before
    public void setUp() {
        userSocialService = new DefaultUserSocialService(userSocialRepository, userHelper);
    }

    @Test
    public void create_Success_IfValid() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(userSocial).when(userSocialRepository).save((UserSocial) anyObject());

        userSocialService.create(userSocial);

        verify(userSocialRepository).save(userSocial);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_ExceptionThrown_ifNull() throws Exception {
        userSocialService.create(null);
    }

    @Test
    public void find_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(userSocial).when(userSocialRepository).findOne(userSocial.getId());

        assertThat(userSocialService.find(userSocial.getId()), is(userSocial));

        verify(userSocialRepository).findOne(userSocial.getId());
    }

    @Test
    public void find_Null_IfNotPresent() throws Exception {
        doReturn(null).when(userSocialRepository).findOne(anyString());

        assertThat(userSocialService.find("id"), Matchers.nullValue());

        verify(userSocialRepository).findOne("id");
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(userSocial).when(userSocialRepository).findByUserId(userSocial.getUserId());

        assertThat(userSocialService.findByUserId(userSocial.getUserId()), is(userSocial));

        verify(userSocialRepository).findByUserId(userSocial.getUserId());
    }

    @Test
    public void findByUserId_Null_IfNotPresent() throws Exception {
        assertThat(userSocialService.findByUserId("userid"), nullValue());

        verify(userSocialRepository).findByUserId("userid");
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();
        ArrayList userList = new ArrayList() {{
            add(userSocial);
        }};

        doReturn(userList).when(userSocialRepository).findAll();

        assertThat(userSocialService.findAll(), containsInAnyOrder(userList.toArray()));

        verify(userSocialRepository).findAll();
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        doReturn(Collections.emptyList()).when(userSocialRepository).findAll();

        assertThat(userSocialService.findAll(), emptyCollectionOf(UserSocial.class));

        verify(userSocialRepository).findAll();
    }

    @Test
    public void update_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(userSocial).when(userSocialRepository).findOne(userSocial.getId());
        doReturn(userSocial).when(userSocialRepository).save((UserSocial) anyObject());

        userSocialService.update(userSocial.getId(), userSocial);

        verify(userSocialRepository).save(userSocial);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_ExceptionThrown_ifNull() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(null).when(userSocialRepository).findOne(userSocial.getId());

        userSocialService.update(userSocial.getId(), null);
    }

    @Test
    public void update_NewCreated_ifNotPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        doReturn(userSocial).when(userSocialRepository).findOne(userSocial.getId());
        doReturn(userSocial).when(userSocialRepository).save((UserSocial) anyObject());

        userSocialService.update("not present", userSocial);

        verify(userSocialRepository).save(userSocial);
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.delete(userSocial.getId());

        verify(userSocialRepository).delete(userSocial.getId());
    }

    @Test
    public void deleteByUserId_Success_IfPresent() throws Exception {
        UserSocial userSocial = createUserSocial();

        userSocialService.deleteByUserId(userSocial.getUserId());

        verify(userSocialRepository).deleteByUserId(userSocial.getUserId());
    }
}
