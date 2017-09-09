package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;
import lombok.AllArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultLinkedInProfileDataService implements LinkedInProfileDataService {

    private final DozerBeanMapper mapper;
    private final LinkedIn linkedIn;

    @Override
    public Optional<LinkedInProfileData> getData(final String userUrl) {
        return Optional.ofNullable(userUrl)
                .map(login -> {
                    final LinkedInProfileFull fullProfile
                            = linkedIn.profileOperations().getProfileFullByPublicUrl(userUrl);

                    return Optional.of(mapper.map(fullProfile, LinkedInProfileData.class));
                }).orElseThrow(() -> new IllegalArgumentException("LinkedIn public user url should not be empty"));
    }
}
