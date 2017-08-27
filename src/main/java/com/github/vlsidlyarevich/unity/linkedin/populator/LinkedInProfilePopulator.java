package com.github.vlsidlyarevich.unity.linkedin.populator;

import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;
import org.springframework.social.linkedin.api.LinkedInProfileFull;

public interface LinkedInProfilePopulator extends LinkedInPopulator<LinkedInProfileData, LinkedInProfileFull> {
}
