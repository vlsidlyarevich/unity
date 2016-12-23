package com.github.vlsidlyarevich.unity.db;

import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TestInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestInitializer.class, args);
    }
}
