package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.domain.model.TwitterProfile;
import com.github.vlsidlyarevich.unity.domain.repository.TwitterProfileRepository;
import com.github.vlsidlyarevich.unity.twitter.service.TwitterPopularProfileService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTwitterProfileService implements TwitterProfileService {

    private final Mapper mapper;
    private final TwitterProfileRepository repository;
    private final TwitterPopularProfileService jsonService;

    @PostConstruct
    public void init() throws IOException {
        jsonService.getPopularProfileList()
                .forEach(profile -> repository.save(mapper.map(profile, TwitterProfile.class)));
    }

    @Override
    public TwitterProfile findByScreenName(final String screenName) {
        return repository.findByUrlEndingWith(screenName);
    }

    @Override
    public List<TwitterProfile> findAll() {
        return repository.findAll();
    }
}
