package com.github.vlsidlyarevich.unity.common.audit;

public interface Auditing {

    void logController(String username, String method, String path);

    void logService(String serviceName, String method, Object[] args);

    void logAdapter(String adapterName);

    void logException(String serviceName, Throwable exception);
}
