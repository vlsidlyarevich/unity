package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;
import com.github.vlsidlyarevich.unity.linkedin.populator.LinkedInProfilePopulator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultLinkedInProfileDataService implements LinkedInProfileDataService {

    //FIXME get from social connections (if user has connected linkedin profile, take it from there)
    private final LinkedIn linkedIn;

    private LinkedInProfilePopulator profilePopulator;

    @Override
    public Optional<LinkedInProfileData> getLinkedInProfileData(final String userUrl) {
        return Optional.ofNullable(userUrl)
                .map(login -> {
                    final LinkedInProfileFull fullProfile
                            = linkedIn.profileOperations().getProfileFullByPublicUrl(userUrl);

                    return Optional.of(profilePopulator.populate(fullProfile));
                }).orElseThrow(() -> new IllegalArgumentException("LinkedIn public user url should not be empty"));
    }
}
