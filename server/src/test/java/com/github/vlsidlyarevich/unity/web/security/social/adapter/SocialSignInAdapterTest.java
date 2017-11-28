package com.github.vlsidlyarevich.unity.web.security.social.adapter;

import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.vlsidlyarevich.unity.TestUtils.createUser;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
public class SocialSignInAdapterTest {

    private static final String SOCIAL_AUTHENTICATION_COOKIE_NAME = "social-authentication";

    @Captor
    private ArgumentCaptor<Cookie> captor;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private TokenService tokenService;

    private SocialSignInAdapter socialSignInAdapter;

    @Before
    public void setUp() {
        this.socialSignInAdapter = new SocialSignInAdapter(userDetailsService, tokenService);
    }

//    @Test
    public void signIn_Success() throws Exception {
        final UserDetails user = createUser();
        final String token = "token";

        final HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        final HttpServletResponse servletResponse = mock(HttpServletResponse.class);

        final ServletWebRequest request = new ServletWebRequest(servletRequest, servletResponse);

        doReturn(user).when(userDetailsService).loadUserByUsername(user.getUsername());
        doReturn(token).when(tokenService).getToken(user.getUsername(), user.getPassword());

        socialSignInAdapter.signIn(user.getUsername(), null, request);

        verify(servletResponse).addCookie(captor.capture());
        assertThat(captor.getValue().getName(), equalTo(SOCIAL_AUTHENTICATION_COOKIE_NAME));
        assertThat(captor.getValue().getValue(), equalTo(token));
    }

}
