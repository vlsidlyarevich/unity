package com.github.vlsidlyarevich.unity.git.factory;

import com.github.vlsidlyarevich.unity.git.config.GitProperties;
import com.github.vlsidlyarevich.unity.git.constant.Github;
import com.github.vlsidlyarevich.unity.web.interceptor.HeaderRequestInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigurableRestTemplateFactory implements RestTemplateFactory {

    private Configuration configuration;

    private final GitProperties gitProperties;

    @Autowired
    public ConfigurableRestTemplateFactory(final GitProperties gitProperties) {
        this.gitProperties = gitProperties;
        this.configuration = new Configuration();
    }

    public RestTemplate getObject() {
        return configureRestTemplate(new RestTemplate());
    }

    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }

    public boolean isSingleton() {
        return true;
    }

    private RestTemplate configureRestTemplate(final RestTemplate restTemplate) {
        restTemplate.setRequestFactory(getClientHttpRequestFactory());
        restTemplate.setInterceptors(getInterceptors());
        return restTemplate;
    }

    private List<ClientHttpRequestInterceptor> getInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Accept",
                Github.MEDIA_HEADER));
        interceptors.add(new HeaderRequestInterceptor("x-oauth-basic",
                gitProperties.getAccessToken()));
        return interceptors;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(configuration.getTimeout())
                .setConnectionRequestTimeout(configuration.getTimeout())
                .setSocketTimeout(configuration.getTimeout())
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    private class Configuration {
        private int timeout = 5000;

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(final int timeout) {
            this.timeout = timeout;
        }
    }
}
