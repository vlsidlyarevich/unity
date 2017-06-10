package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUser;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() {
        this.user = createUser();
    }

    @Test
    public void findByUsername_Success_ifValid() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        final User result = userRepository.findByUsername(user.getUsername());

        verify(userRepository).findByUsername(user.getUsername());
        Assert.assertEquals(user, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsername_ExceptionThrown_ifNull() {
        when(userRepository.findByUsername(null)).thenThrow(IllegalArgumentException.class);
        userRepository.findByUsername(null);
    }
}

