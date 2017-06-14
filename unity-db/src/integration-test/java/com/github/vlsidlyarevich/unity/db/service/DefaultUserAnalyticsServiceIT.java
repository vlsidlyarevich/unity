package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.db.UnityDatabaseIT;
import com.github.vlsidlyarevich.unity.db.domain.UserAnalytics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.vlsidlyarevich.unity.db.TestUtils.createAnalysisReport;
import static com.github.vlsidlyarevich.unity.db.TestUtils.createUserAnalytics;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnityDatabaseIT.class)
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

    @Test
    public void find_Null_IfNotPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        userAnalytics.setId("id");

        Assert.assertThat(userAnalyticsService.find(userAnalytics.getId()), nullValue());
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.add(userAnalytics);

        Assert.assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()), is(userAnalytics));
    }

    @Test
    public void findByUserId_Null_IfNotPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        Assert.assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()), nullValue());
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
