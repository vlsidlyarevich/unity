package com.github.vlsidlyarevich.unity.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    private final ResourceProperties resourceProperties;

    @Autowired
    public WebConfig(final ResourceProperties resourceProperties) {
        this.resourceProperties = resourceProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Integer cachePeriod = resourceProperties.getCachePeriod();

        final String[] staticLocations = resourceProperties.getStaticLocations();
        final String[] indexLocations = new String[staticLocations.length];
        for (int i = 0; i < staticLocations.length; i++) {
            indexLocations[i] = staticLocations[i] + "index.html";
        }
        registry.addResourceHandler(
                "/**/*.css",
                "/**/*.html",
                "/**/*.js",
                "/**/*.json",
                "/**/*.bmp",
                "/**/*.jpeg",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.ttf",
                "/**/*.eot",
                "/**/*.svg",
                "/**/*.woff",
                "/**/*.woff2"
        )
                .addResourceLocations(staticLocations)
                .setCachePeriod(cachePeriod);

        registry.addResourceHandler("/**")
                .addResourceLocations(indexLocations)
                .setCachePeriod(cachePeriod)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {
                        return location.exists() && location.isReadable() ? location
                                : null;
                    }
                });
    }
}