package com.github.vlsidlyarevich.unity.domain.repository;

import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createUserAnalytics;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAnalyticsRepositoryIT {

    @Autowired
    private UserAnalyticsRepository userAnalyticsRepository;

    @After
    public void cleanUp() {
        userAnalyticsRepository.deleteAll();
    }

    @Test
    public void findByUserId_Success_ifValid() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsRepository.save(userAnalytics);

        UserAnalytics savedUserAnalytics = userAnalyticsRepository.findByUserId(userAnalytics.getUserId());

        Assert.assertNotNull(savedUserAnalytics);
        Assert.assertNotNull(savedUserAnalytics.getId());
        Assert.assertEquals(userAnalytics.getUserId(), savedUserAnalytics.getUserId());
    }
}
