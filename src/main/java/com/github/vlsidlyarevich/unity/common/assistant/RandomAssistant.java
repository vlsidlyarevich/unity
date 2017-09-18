package com.github.vlsidlyarevich.unity.common.assistant;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public final class RandomAssistant {

    private static final Integer RANDOM_STRING_LENGTH = 6;

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(RANDOM_STRING_LENGTH);
    }

    public String randomAlphaNumeric(final Integer stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }
}
