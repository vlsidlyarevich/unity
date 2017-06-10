package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserSocial;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserSocialRepositoryTest {

    @Mock
    private UserSocialRepository userSocialRepository;
    private UserSocial userSocial;

    @Before
    public void setUp() {
        this.userSocial = createUserSocial();
    }

    @Test
    public void findByUserId_Success_ifValid() {
        when(userSocialRepository.findByUserId(userSocial.getUserId())).thenReturn(userSocial);

        final UserSocial result = userSocialRepository.findByUserId(userSocial.getUserId());

        verify(userSocialRepository).findByUserId(userSocial.getUserId());
        Assert.assertEquals(userSocial, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUserId_ExceptionThrown_ifNull() {
        when(userSocialRepository.findByUserId(null)).thenThrow(IllegalArgumentException.class);
        userSocialRepository.findByUserId(null);
    }
}
