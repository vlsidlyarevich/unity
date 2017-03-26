package com.github.vlsidlyarevich.unity.git.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "git")
public class GitProperties {

    private String apiUrl;

}
