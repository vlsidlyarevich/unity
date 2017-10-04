package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.GitResult;
import com.github.vlsidlyarevich.unity.common.service.AbstractAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGitAnalyzeService extends AbstractAnalyzeService<GitProfileDataService, GitResult>
        implements GitAnalyzeService {

    @Autowired
    public DefaultGitAnalyzeService(final GitProfileDataService service) {
        super(service);
    }
}
