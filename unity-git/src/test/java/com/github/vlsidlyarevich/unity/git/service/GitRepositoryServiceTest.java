package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepositoryServiceTest {

    @Autowired
    private GitRepositoryService gitRepositoryService;

    @Test
    public void getGitRepositoriesTest() throws Exception {
        Optional<List<GitRepository>> gitRepositories = gitRepositoryService.getGitRepositories("vlsidlyarevich");
        Assert.assertNotNull(gitRepositories);
        Assert.assertTrue(gitRepositories.isPresent());
    }

    @Test
    public void getGitRepositoriesNotFoundTest() throws Exception {
        Optional<List<GitRepository>> gitRepositories = gitRepositoryService.getGitRepositories("thereisnosuchprofilewiththatname");
        Assert.assertNotNull(gitRepositories);
        Assert.assertTrue(!gitRepositories.isPresent());
    }

    @Test
    public void getGitRepositoryTest() throws Exception {
        Optional<GitRepository> gitRepository = gitRepositoryService.getGitRepository("vlsidlyarevich", "unity");
        Assert.assertNotNull(gitRepository);
        Assert.assertTrue(gitRepository.isPresent());
        Assert.assertNotNull(gitRepository.get().getName());
        Assert.assertNotNull(gitRepository.get().getUrl());
        Assert.assertNotNull(gitRepository.get().getGitTagsUrl());
        Assert.assertNotNull(gitRepository.get().getTagsUrl());
        Assert.assertNotNull(gitRepository.get().getLabelsUrl());
        Assert.assertNotNull(gitRepository.get().getLanguage());
        Assert.assertNotNull(gitRepository.get().getLanguagesUrl());
    }

    @Test
    public void getGitRepositoryNotFoundTest() throws Exception {
        Optional<GitRepository> gitRepository = gitRepositoryService.getGitRepository("vlsidlyarevich", "thereisnosuchrepowiththatname");
        Assert.assertNotNull(gitRepository);
        Assert.assertTrue(!gitRepository.isPresent());
    }
}
