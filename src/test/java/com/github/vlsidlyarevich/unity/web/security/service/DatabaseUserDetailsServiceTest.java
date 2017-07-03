package com.github.vlsidlyarevich.unity.web.security.service;

import com.github.vlsidlyarevich.unity.db.domain.User;
import com.github.vlsidlyarevich.unity.db.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DatabaseUserDetailsServiceTest {

    private DatabaseUserDetailsService service;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        this.service = new DatabaseUserDetailsService(userService);
    }

    @Test
    public void loadUserByUsername_Success_IfPresent() throws Exception {
        User user = createUser();

        doReturn(user).when(userService).findByUsername(user.getUsername());

        Assert.assertThat(service.loadUserByUsername(user.getUsername()), is(user));

        verify(userService).findByUsername(user.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_ExceptionThrown_IfNotPresent() throws Exception {
        service.loadUserByUsername("username");
    }
}
