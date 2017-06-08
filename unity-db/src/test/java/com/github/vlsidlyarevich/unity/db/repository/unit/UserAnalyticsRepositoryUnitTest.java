package com.github.vlsidlyarevich.unity.db.repository.unit;

import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserAnalytics;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserAnalyticsRepositoryUnitTest {

    private UserAnalyticsRepository userAnalyticsRepositoryMock;
    private UserAnalytics userAnalytics;

    @Before
    public void setUp() {
        this.userAnalyticsRepositoryMock = Mockito.mock(UserAnalyticsRepository.class);
        this.userAnalytics = createUserAnalytics();
    }

    @Test
    public void findByUserIdWithStoredSuccessfulyTest() {
        when(userAnalyticsRepositoryMock.findByUserId(userAnalytics.getUserId())).thenReturn(userAnalytics);

        final UserAnalytics result = userAnalyticsRepositoryMock.findByUserId(userAnalytics.getUserId());

        verify(userAnalyticsRepositoryMock).findByUserId(userAnalytics.getUserId());
        Assert.assertEquals(userAnalytics, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUserIdWithNullTest() {
        when(userAnalyticsRepositoryMock.findByUserId(null)).thenThrow(IllegalArgumentException.class);
        userAnalyticsRepositoryMock.findByUserId(null);
    }
}
