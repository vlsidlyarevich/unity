package com.github.vlsidlyarevich.unity.linkedin.service;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import com.github.vlsidlyarevich.unity.common.service.AbstractAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLinkedInAnalyzeService extends AbstractAnalyzeService<LinkedInProfileDataService, LinkedInResult>
        implements LinkedInAnalyzeService {

    @Autowired
    public DefaultLinkedInAnalyzeService(final LinkedInProfileDataService service) {
        super(service);
    }
}
