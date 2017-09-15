package com.github.vlsidlyarevich.unity.common.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerBasedAuditor implements Auditing {

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

    @Override
    public void logService(final String serviceName, final String method,
                           final Object[] args) {
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("Service name is mandatory");
        }

        if (StringUtils.isBlank(method)) {
            throw new IllegalArgumentException("Method is mandatory");
        }

        log.info("Message : Called service: {} for method: {} with arguments: {}",
                serviceName, method, args);
    }

    @Override
    public void logAdapter(final String adapterName) {
        if (StringUtils.isBlank(adapterName)) {
            throw new IllegalArgumentException("Adapter name is mandatory");
        }

        log.info("Message : Called adapter: {}", adapterName);
    }

    @Override
    public void logException(final String serviceName, final Throwable exception) {
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("Service name is mandatory");
        }

        log.info("Message : Throwed exception: '{}' in service: {}",
                exception, serviceName);
    }
}
