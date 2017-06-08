package com.github.vlsidlyarevich.unity.db;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public final class TestRandomUtils {

    private static final int MAX_STRING_LENGTH = 15;
    private static final int START_INCLUSIVE = 5000;
    private static final int END_INCLUSIVE = 100000;

    private TestRandomUtils() {

    }

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public static Integer getRandomInt(int startInclusive, int endInclusive) {
        return RandomUtils.nextInt(startInclusive, endInclusive);
    }
}
