package com.github.vlsidlyarevich.unity.web.config;

import com.github.vlsidlyarevich.unity.web.interceptor.RequestLoggingInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private final RequestLoggingInterceptor requestLoggingInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor).addPathPatterns("/**");
    }
}
