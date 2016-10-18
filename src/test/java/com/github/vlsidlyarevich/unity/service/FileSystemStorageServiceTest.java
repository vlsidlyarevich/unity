package com.github.vlsidlyarevich.unity.service;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.utils.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
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

//    @After
//    public void cleanUp() {
//        storageService.deleteAll();
//    }

    @Test
    public void storeTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "file.gif", "image/png", "nonsensecontent".getBytes());

        String id = storageService.store(file);

        Assert.assertEquals(storageService.load(id).toFile().getName(), id);
        Assert.assertEquals(storageService.load(id).toFile().getFreeSpace(),
                FileUtils.multipartToFile(file, id).getFreeSpace());
        Assert.assertEquals(storageService.load(id).toFile().getTotalSpace(),
                FileUtils.multipartToFile(file, id).getTotalSpace());
        Assert.assertEquals(storageService.load(id).toFile().getUsableSpace(),
                FileUtils.multipartToFile(file, id).getUsableSpace());
    }

    @Test
    public void loadAllTest() throws Exception {
        MultipartFile file1 = new MockMultipartFile("file1", "file.gif", "image/png", "nonsensecontent 1".getBytes());
        MultipartFile file2 = new MockMultipartFile("file2", "file.gif", "image/png", "nonsensecontent 2".getBytes());

        String id1 = storageService.store(file1);
        String id2 = storageService.store(file2);
        List<File> savedFiles = new ArrayList<>();
        savedFiles.add(FileUtils.multipartToFile(file1, id1));
        savedFiles.add(FileUtils.multipartToFile(file2, id2));

        List<Path> resolvedFiles = storageService.loadAll();
        resolvedFiles.forEach(Path::toFile);

        Assert.assertThat(storageService.loadAll().size(), is(2));
    }

}
