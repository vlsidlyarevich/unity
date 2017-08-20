package com.github.vlsidlyarevich.unity.common.assistant;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomAssistant {

    private static final Integer RANDOM_STRING_LENGTH = 6;

    private RandomAssistant() {

    }

    public static String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(RANDOM_STRING_LENGTH);
    }

    public static String randomAlphaNumeric(final Integer stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }
}
