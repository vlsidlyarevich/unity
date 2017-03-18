package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GitProfileServiceTest {

    @Autowired
    private GitProfileService gitProfileService;

    @Test
    public void getGitProfileTest() throws Exception {
        GitProfile gitProfile = gitProfileService.getGitProfile("vlsidlyarevich");
        Assert.assertNotNull(gitProfile);
        Assert.assertNotNull(gitProfile.getPublicRepos());
        Assert.assertNotNull(gitProfile.getReposUrl());
        Assert.assertNotNull(gitProfile.getUrl());
        Assert.assertNotNull(gitProfile.getName());
        Assert.assertNotNull(gitProfile.getLogin());
    }
}
