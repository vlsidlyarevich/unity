package com.github.vlsidlyarevich.unity.web.config;

import lombok.AllArgsConstructor;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DozerConfig {

    private static final String PACKAGE_TO_SCAN = "com.github.vlsidlyarevich.unity.mapping";

    private final List<CustomConverter> customConverters;

    @Bean
    public DozerBeanMapper mapper() throws ReflectiveOperationException {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        final List<BeanMappingBuilder> registeredMapping = getRegisteredMappings();
        registeredMapping.forEach(mapper::addMapping);
        mapper.setCustomConvertersWithId(getCustomConvertersWithId());

        return mapper;
    }

    private List<BeanMappingBuilder> getRegisteredMappings() throws ReflectiveOperationException {
        final ClassPathScanningCandidateComponentProvider scanner
                = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(BeanMappingBuilder.class));

        final List<BeanMappingBuilder> customMapping = new ArrayList<>();

        for (final BeanDefinition bd : scanner.findCandidateComponents(PACKAGE_TO_SCAN)) {
            customMapping.add((BeanMappingBuilder) Class.forName(bd.getBeanClassName()).newInstance());
        }

        return customMapping;
    }

    private Map<String, CustomConverter> getCustomConvertersWithId() {
        final Map<String, CustomConverter> customConvertersMap = new HashMap<>();
        customConverters
                .forEach(converter ->
                        customConvertersMap.put(converter.getClass().getSimpleName(), converter));

        return customConvertersMap;
    }
}
