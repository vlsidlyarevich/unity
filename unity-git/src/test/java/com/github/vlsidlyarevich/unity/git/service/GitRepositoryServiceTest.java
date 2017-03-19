package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.exception.GitRepositoryNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepositoryServiceTest {

    @Autowired
    private GitRepositoryService gitRepositoryService;

    @Test
    public void getGitRepositoryTest() throws Exception {

    }

    @Test(expected = GitRepositoryNotFoundException.class)
    public void getGitRepositoryNotFoundTest() throws Exception {

    }
}
