package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import com.github.vlsidlyarevich.unity.db.exception.ResourceNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.TestUtils.createAnalysisReport;
import static com.github.vlsidlyarevich.unity.TestUtils.createUserAnalytics;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultUserAnalyticsServiceIT {

    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @Before
    public void setUp() {
        userAnalyticsService.deleteAll();
    }

    @After
    public void cleanUp() {
        userAnalyticsService.deleteAll();
    }

    @Test
    public void add_Success_IfValid() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);

        Assert.assertThat(userAnalyticsService.findAll().size(), is(1));
        Assert.assertThat(userAnalyticsService.find(userAnalytics.getId()), is(userAnalytics));
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ExceptionThrown_IfNull() throws Exception {
        userAnalyticsService.add(null);
    }

    @Test
    public void find_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);

        Assert.assertThat(userAnalyticsService.findAll().size(), is(1));
        Assert.assertThat(userAnalyticsService.find(userAnalytics.getId()), notNullValue());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void find_ExceptionThrown_IfNotPresent() throws Exception {
        Assert.assertThat(userAnalyticsService.find("id"), nullValue());
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);

        Assert.assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()), is(userAnalytics));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByUserId_ExceptionThrown_IfNotPresent() throws Exception {
        Assert.assertThat(userAnalyticsService.findByUserId("userid"), nullValue());
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);

        Assert.assertThat(userAnalyticsService.findAll().size(), is(1));
        Assert.assertTrue(userAnalyticsService.findAll().contains(userAnalytics));
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        Assert.assertTrue(userAnalyticsService.findAll().isEmpty());
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);
        userAnalyticsService.delete(userAnalytics.getId());

        Assert.assertTrue(userAnalyticsService.findAll().isEmpty());
    }

    @Test
    public void deleteReport_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        AnalysisReport analysisReport = createAnalysisReport();
        userAnalytics.getReports().add(analysisReport);

        userAnalyticsService.add(userAnalytics);
        userAnalyticsService.deleteReport(userAnalytics.getUserId(), analysisReport.getId());

        Assert.assertTrue(userAnalyticsService.find(userAnalytics.getId()).getReports().isEmpty());
    }

    @Test
    public void deleteAll_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);
        userAnalyticsService.deleteAll();

        Assert.assertTrue(userAnalyticsService.findAll().isEmpty());
    }

    @Test
    public void deleteAllReports_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        AnalysisReport analysisReport = createAnalysisReport();
        userAnalytics.getReports().add(analysisReport);

        userAnalyticsService.add(userAnalytics);
        userAnalyticsService.deleteAllReports(userAnalytics.getUserId());

        Assert.assertTrue(userAnalyticsService.find(userAnalytics.getId()).getReports().isEmpty());
    }
}
