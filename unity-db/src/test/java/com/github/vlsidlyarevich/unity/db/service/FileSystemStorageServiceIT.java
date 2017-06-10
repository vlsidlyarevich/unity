package com.github.vlsidlyarevich.unity.db.service;

import com.github.vlsidlyarevich.unity.db.UnityDatabaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = UnityDatabaseTest.class)
public class FileSystemStorageServiceIT {

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

        Assert.assertTrue(storageService.loadAll().size() == 2);
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
