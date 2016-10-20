package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vladislav on 10/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class FileSystemStorageServiceTest {

    @Autowired
    private StorageService storageService;

    @After
    public void cleanUp() {
        storageService.deleteAll();
    }

    @Test
    public void storeTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.load(id).toFile().getName(), id);
        Assert.assertEquals(storageService.load(id).toFile().length(), file.getSize());
    }

    @Test
    public void loadAllTest() throws Exception {
        MultipartFile file1 = new MockMultipartFile("file1", "file.gif", "image/png", "nonsensecontent 1".getBytes());
        MultipartFile file2 = new MockMultipartFile("file2", "file.gif", "image/png", "nonsensecontent 2".getBytes());

        String id1 = storageService.store(file1);
        String id2 = storageService.store(file2);

        List<Path> resolvedFiles = storageService.loadAll();
        resolvedFiles.forEach(Path::toFile);

        Assert.assertThat(storageService.loadAll().size(), is(2));
    }

    @Test
    public void loadAsResourceTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.loadAsResource(id).getFilename(), id);
        Assert.assertEquals(storageService.loadAsResource(id).contentLength(), file.getSize());
    }

    @Test
    public void deleteTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        storageService.delete(id);
        Assert.assertEquals(storageService.loadAll().size(), 0);
    }
}
