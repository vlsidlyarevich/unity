package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.git.model.GitProfile;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GitProfileServiceTest {

    @Autowired
    private GitProfileService gitProfileService;

//    @Test
    public void getGitProfileTest() throws Exception {
        Optional<GitProfile> gitProfile = gitProfileService.getGitProfile("vlsidlyarevich");
        Assert.assertNotNull(gitProfile);
        Assert.assertTrue(gitProfile.isPresent());
        Assert.assertNotNull(gitProfile.get().getPublicRepos());
        Assert.assertNotNull(gitProfile.get().getReposUrl());
        Assert.assertNotNull(gitProfile.get().getUrl());
        Assert.assertNotNull(gitProfile.get().getName());
        Assert.assertNotNull(gitProfile.get().getLogin());
    }

//    @Test
    public void getGitProfileNotFoundTest() throws Exception {
        Optional<GitProfile> gitProfile = gitProfileService.getGitProfile("thereisnosuchprofilewiththatname");
        Assert.assertNotNull(gitProfile);
        Assert.assertTrue(!gitProfile.isPresent());
    }
}
