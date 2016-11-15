package com.github.vlsidlyarevich.unity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String path;

}
