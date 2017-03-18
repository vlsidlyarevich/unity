package com.github.vlsidlyarevich.unity.git;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(GitProperties.class)
public class UnityGit {

}
