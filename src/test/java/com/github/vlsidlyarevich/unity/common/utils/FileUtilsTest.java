package com.github.vlsidlyarevich.unity.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Path;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileUtilsTest {

    @Mock
    private Path path;

    @Mock
    private File file;

    @Test
    public void cleanDirectory_Success_IfNotNull() throws Exception {
        when(path.toFile()).thenReturn(file);
        when(file.listFiles()).thenReturn(new File[]{file});

        FileUtils.cleanDirectory(path);

        verify(file).delete();
    }
}
