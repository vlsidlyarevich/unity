package com.github.vlsidlyarevich.unity.db;

import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UnityDatabase {

}
