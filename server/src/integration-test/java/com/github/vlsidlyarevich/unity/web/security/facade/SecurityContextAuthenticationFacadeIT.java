package com.github.vlsidlyarevich.unity.web.security.facade;

import com.github.vlsidlyarevich.unity.web.security.model.UserAuthentication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityContextAuthenticationFacadeIT {

    @Autowired
    private AuthenticationFacade facade;

    @Test
    public void getAuthentication_Success_IfValid() throws Exception {
        SecurityContext context = new SecurityContextImpl();
        UserAuthentication authentication = new UserAuthentication(createUser());
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        Assert.assertThat(facade.getAuthentication(), is(authentication));
    }
}
