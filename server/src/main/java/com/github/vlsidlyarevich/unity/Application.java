package com.github.vlsidlyarevich.unity;

import com.github.vlsidlyarevich.unity.domain.config.StorageProperties;
import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.twitter.config.TwitterProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties({StorageProperties.class, GitProperties.class, TwitterProperties.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
