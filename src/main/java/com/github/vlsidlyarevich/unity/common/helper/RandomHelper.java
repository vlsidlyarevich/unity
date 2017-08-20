package com.github.vlsidlyarevich.unity.common.helper;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomHelper {

    private static final Integer RANDOM_STRING_LENGTH = 6;

    private RandomHelper() {

    }

    public static String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(RANDOM_STRING_LENGTH);
    }

    public static String randomAlphaNumeric(final Integer stringLength) {
        return RandomStringUtils.randomAlphanumeric(stringLength);
    }
}
