package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vladislav on 10/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class FileSystemStorageServiceTest {

    @Autowired
    private StorageService storageService;

    @Test
    public void storeTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.load(id), file);
    }

}
