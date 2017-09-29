package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;
import com.github.vlsidlyarevich.unity.domain.repository.TwitterProfileRepository;
import com.github.vlsidlyarevich.unity.twitter.model.TwitterPopularProfile;
import com.github.vlsidlyarevich.unity.twitter.service.TwitterPopularProfileService;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.github.vlsidlyarevich.unity.TestUtils.createPopularProfile;
import static com.github.vlsidlyarevich.unity.TestUtils.createTwitterProfile;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class DefaultTwitterProfileServiceTest {

    private TwitterProfileService profileService;

    @Mock
    private Mapper mapper;

    @Mock
    private TwitterProfileRepository repository;

    @Mock
    private TwitterPopularProfileService jsonService;

    @Before
    public void setUp() {
        this.profileService = new DefaultTwitterProfileService(mapper, repository, jsonService);
    }

    @Test
    public void init_Success() throws Exception {
        TwitterPopularProfile popularProfile = createPopularProfile();
        TwitterProfile twitterProfile = createTwitterProfile(popularProfile);

        doReturn(twitterProfile).when(mapper).map(popularProfile, TwitterProfile.class);

        doReturn(new ArrayList<TwitterPopularProfile>() {{
            add(popularProfile);
        }}).when(jsonService).getPopularProfileList();

        ((DefaultTwitterProfileService) profileService).init();

        verify(repository).save(twitterProfile);
    }

    @Test
    public void findByScreenName_Success_IfPresent() {
        return repository.findByUrlEndingWith(screenName);
    }

    @Test
    public void findByScreenName_Null_IfNotPresent() {
        return repository.findByUrlEndingWith(screenName);
    }

    @Test
    public void findAll_Success_IfPresent() {
        return repository.findAll();
    }

    @Test
    public void findAll_EmptyCollection_IfNotPresent() {
        return repository.findAll();
    }
}
