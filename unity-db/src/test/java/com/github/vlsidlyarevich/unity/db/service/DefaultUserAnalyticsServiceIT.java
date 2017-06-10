package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
public class DefaultUserAnalyticsServiceIT {

    @Test
    public void add() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void findByUserId() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void deleteReport() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {

    }

    @Test
    public void deleteAllReports() throws Exception {

    }
}
