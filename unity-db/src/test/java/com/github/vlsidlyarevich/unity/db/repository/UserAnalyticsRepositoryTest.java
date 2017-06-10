package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserAnalytics;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserAnalyticsRepositoryTest {

    @Mock
    private UserAnalyticsRepository userAnalyticsRepository;
    private UserAnalytics userAnalytics;

    @Before
    public void setUp() {
        this.userAnalytics = createUserAnalytics();
    }

    @Test
    public void findByUserId_Success_ifValid() {
        when(userAnalyticsRepository.findByUserId(userAnalytics.getUserId())).thenReturn(userAnalytics);

        final UserAnalytics result = userAnalyticsRepository.findByUserId(userAnalytics.getUserId());

        verify(userAnalyticsRepository).findByUserId(userAnalytics.getUserId());
        Assert.assertEquals(userAnalytics, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsername_ExceptionThrown_ifNull() {
        when(userAnalyticsRepository.findByUserId(null)).thenThrow(IllegalArgumentException.class);
        userAnalyticsRepository.findByUserId(null);
    }
}
