package com.github.vlsidlyarevich.unity.web;

import com.github.vlsidlyarevich.unity.auth.AuthConfiguration;
import com.github.vlsidlyarevich.unity.db.DatabaseConfiguration;
import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;


@Import({
        DatabaseConfiguration.class,
        AuthConfiguration.class
})
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
