package com.github.vlsidlyarevich.unity.db.repository.unit;

import com.github.vlsidlyarevich.unity.db.TestRandomUtils;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.db.repository.UserSocialRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserSocial;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserSocialRepositoryUnitTest {

    private UserSocialRepository userSocialRepositoryMock;
    private UserSocial userSocial;

    @Before
    public void setUp() {
        this.userSocialRepositoryMock = Mockito.mock(UserSocialRepository.class);
        this.userSocial = createUserSocial();
    }

    @Test
    public void findByUserIdWithStoredSuccessfulyTest() {
        when(userSocialRepositoryMock.findByUserId(userSocial.getUserId())).thenReturn(userSocial);

        final UserSocial result = userSocialRepositoryMock.findByUserId(userSocial.getUserId());

        verify(userSocialRepositoryMock).findByUserId(userSocial.getUserId());
        Assert.assertEquals(userSocial, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUserIdWithNullTest() {
        when(userSocialRepositoryMock.findByUserId(null)).thenThrow(IllegalArgumentException.class);
        userSocialRepositoryMock.findByUserId(null);
    }
}
