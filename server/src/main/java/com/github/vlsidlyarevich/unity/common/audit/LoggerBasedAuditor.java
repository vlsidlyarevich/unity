package com.github.vlsidlyarevich.unity.common.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerBasedAuditor implements Auditing {

    @Override
    public void logController(final String username, final String methodName,
                              final String path) {
        validateMethodName(methodName);
        validatePath(path);

        log.info("Audit Log from User: {} - "
                + "Message : Called controller for path: {} - {}", username, methodName, path);
    }

    @Override
    public void logService(final String serviceName, final String methodName,
                           final Object[] args) {
        validateServiceName(serviceName);
        validateMethodName(methodName);

        log.info("Message : Called service: {} for method: {} with arguments: {}",
                serviceName, methodName, args);
    }

    @Override
    public void logAdapter(final String adapterName) {
        validateAdapterName(adapterName);

        log.info("Message : Called adapter: {}", adapterName);
    }

    @Override
    public void logException(final String serviceName, final Throwable exception) {
        validateServiceName(serviceName);

        log.info("Message : Throwed exception: '{}' in service: {}",
                exception, serviceName);
    }

    private void validateMethodName(final String methodName) {
        if (StringUtils.isBlank(methodName)) {
            throw new IllegalArgumentException("Method is mandatory");
        }
    }

    private void validateServiceName(final String serviceName) {
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("Service name is mandatory");
        }
    }

    private void validatePath(final String path) {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("Path is mandatory");
        }
    }

    private void validateAdapterName(final String adapterName) {
        if (StringUtils.isBlank(adapterName)) {
            throw new IllegalArgumentException("Adapter name is mandatory");
        }
    }
}
