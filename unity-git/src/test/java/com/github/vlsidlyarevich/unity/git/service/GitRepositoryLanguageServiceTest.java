package com.github.vlsidlyarevich.unity.git.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepositoryLanguageServiceTest {

    @Autowired
    private GitRepositoryLanguageService gitRepositoryLanguageService;

    @Test
    public void getGitRepoLanguagesFirstTest() throws Exception {
        Optional<Map<String,String>> result = gitRepositoryLanguageService
                .getGitRepoLanguages("https://api.github.com/repos/vlsidlyarevich/unity/languages");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void getGitRepoLanguagesNotFoundFirstTest() throws Exception {
        Optional<Map<String,String>> result = gitRepositoryLanguageService
                .getGitRepoLanguages("https://api.github.com/repos/vlsidlyarevich/thereisnorepowiththisname/languages");
        Assert.assertNotNull(result);
        Assert.assertTrue(!result.isPresent());
    }

    @Test
    public void getGitRepoLanguagesSecondTest() throws Exception {
        Optional<Map<String,String>> result = gitRepositoryLanguageService
                .getGitRepoLanguages("vlsidlyarevich","unity");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void getGitRepoLanguagesNotFoundSecondTest() throws Exception {
        Optional<Map<String,String>> result = gitRepositoryLanguageService
                .getGitRepoLanguages("vlsidlyarevich","thereisnorepowiththisname");
        Assert.assertNotNull(result);
        Assert.assertTrue(!result.isPresent());
    }
}
