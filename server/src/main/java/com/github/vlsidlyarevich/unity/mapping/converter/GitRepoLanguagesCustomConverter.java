package com.github.vlsidlyarevich.unity.mapping.converter;

import com.github.vlsidlyarevich.unity.git.service.GitRepositoryLanguageService;
import lombok.AllArgsConstructor;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GitRepoLanguagesCustomConverter implements CustomConverter {

    private final GitRepositoryLanguageService gitRepositoryLanguageService;

    @Override
    public Object convert(final Object languagesMap, final Object languagesUrl,
                          final Class<?> aClass, final Class<?> aClass1) {
        return gitRepositoryLanguageService
                .getGitRepoLanguages((String) languagesUrl)
                .orElse(new HashMap<>());
    }
}
