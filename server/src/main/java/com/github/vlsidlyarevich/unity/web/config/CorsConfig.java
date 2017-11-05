package com.github.vlsidlyarevich.unity.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    private static final String[] CORS_ALLOWED_METHODS = {"POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH"};
    private static final long CORS_MAX_AGE = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(CORS_ALLOWED_METHODS)
                .maxAge(CORS_MAX_AGE);
    }
}
