package com.github.vlsidlyarevich.unity.domain.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileSystemStorageServiceIT {

    @Autowired
    private StorageService storageService;

    @After
    public void cleanUp() {
        storageService.deleteAll();
    }

    @Test
    public void store_Success_IfValidFile() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.load(id).toFile().getName(), id);
        Assert.assertEquals(storageService.load(id).toFile().length(), file.getSize());
    }

    @Test
    public void loadAll_Success_IfPresent() throws Exception {
        MultipartFile file1 = new MockMultipartFile("file1", "file.gif", "image/png", "nonsensecontent 1".getBytes());
        MultipartFile file2 = new MockMultipartFile("file2", "file.gif", "image/png", "nonsensecontent 2".getBytes());

        storageService.store(file1);
        storageService.store(file2);

        Assert.assertTrue(storageService.loadAll().size() == 2);
    }

    @Test
    public void loadAsResource_Success_IfValidFile() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.loadAsResource(id).getFilename(), id);
        Assert.assertEquals(storageService.loadAsResource(id).contentLength(), file.getSize());
    }

    @Test
    public void delete_Success_IfFilePresent() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        storageService.delete(id);
        Assert.assertEquals(storageService.loadAll().size(), 0);
    }

    @Test
    public void deleteAll_Success_IfFilePresent() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        storageService.store(file);

        storageService.deleteAll();
        Assert.assertEquals(storageService.loadAll().size(), 0);
    }
}
