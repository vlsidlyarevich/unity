package com.github.vlsidlyarevich.unity;

import com.github.vlsidlyarevich.unity.db.config.StorageProperties;
import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, GitProperties.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
