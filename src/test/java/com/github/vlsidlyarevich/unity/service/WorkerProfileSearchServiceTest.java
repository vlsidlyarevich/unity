package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.repository.WorkerProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vlad on 02.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class WorkerProfileSearchServiceTest {

    @Autowired
    private WorkerProfileSearchService workerProfileSearchService;

    @Test
    public void findByFiltersTest(){
        workerProfileSearchService.findByFilters(null);
    }

}
