package com.github.vlsidlyarevich.unity.common.audit;

public interface Auditor {

    void logController(String username, String method, String path);

    void logService(String serviceName, String method, Object[] args);
}
