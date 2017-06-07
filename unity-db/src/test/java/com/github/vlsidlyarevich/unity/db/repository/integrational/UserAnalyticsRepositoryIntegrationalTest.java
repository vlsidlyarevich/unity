package com.github.vlsidlyarevich.unity.db.repository.integrational;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.TestUtils;
import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.repository.UserAnalyticsRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
public class UserAnalyticsRepositoryIntegrationalTest {

    @Autowired
    private UserAnalyticsRepository userAnalyticsRepository;

    @After
    public void cleanUp() {
        userAnalyticsRepository.deleteAll();
    }

    @Test
    public void findByUserIdTest() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsRepository.save(userAnalytics);

        UserAnalytics savedUserAnalytics = userAnalyticsRepository.findByUserId(userAnalytics.getUserId());

        Assert.assertNotNull(savedUserAnalytics);
        Assert.assertNotNull(savedUserAnalytics.getId());
        Assert.assertEquals(userAnalytics.getUserId(), savedUserAnalytics.getUserId());
    }

    private UserAnalytics createUserAnalytics() {
        List<AnalysisReport> reports = new ArrayList<>();

        return new UserAnalytics(TestUtils.getRandomString(8), reports);
    }
}
