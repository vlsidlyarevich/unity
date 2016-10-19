package com.github.vlsidlyarevich.unity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by vladislav on 10/19/16.
 */
@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String path;

}
