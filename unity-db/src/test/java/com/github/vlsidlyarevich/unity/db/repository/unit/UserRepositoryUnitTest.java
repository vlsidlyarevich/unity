package com.github.vlsidlyarevich.unity.db.repository.unit;

import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.domain.Authority;
import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserRepositoryUnitTest {

    private UserRepository userRepositoryMock;
    private User user;

    @Before
    public void setUp() {
        this.userRepositoryMock = Mockito.mock(UserRepository.class);
        this.user = createUser();
    }

    @Test
    public void findByUsernameWithStoredSuccessfulyTest() {
        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(user);

        final User result = userRepositoryMock.findByUsername(user.getUsername());

        verify(userRepositoryMock).findByUsername(user.getUsername());
        Assert.assertEquals(user, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsernameWithNullTest() {
        when(userRepositoryMock.findByUsername(null)).thenThrow(IllegalArgumentException.class);
        userRepositoryMock.findByUsername(null);
    }

    private User createUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return new User(authorities, TestUtils.getRandomString(8), TestUtils.getRandomString(8),
                false, false, false,
                true);
    }
}

