package com.github.vlsidlyarevich.unity.db.repository;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserAnalytics;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
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
