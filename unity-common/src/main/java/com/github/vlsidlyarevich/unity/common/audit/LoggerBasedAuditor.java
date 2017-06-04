package com.github.vlsidlyarevich.unity.common.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerBasedAuditor implements Auditor {

    @Override
    public void logController(final String username, final String method,
                              final String path) {
        if (StringUtils.isBlank(method)) {
            throw new IllegalArgumentException("Method is mandatory");
        }

        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("Path is mandatory");
        }

        log.info("Audit Log from User: {} - "
                + "Message : Called controller for path: {} - {}", username, method, path);
    }
}
