package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import com.github.vlsidlyarevich.unity.common.service.AbstractAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTwitterAnalyzeService extends AbstractAnalyzeService<TwitterProfileDataService, TwitterResult>
        implements TwitterAnalyzeService {

    @Autowired
    public DefaultTwitterAnalyzeService(final TwitterProfileDataService service) {
        super(service);
    }
}
