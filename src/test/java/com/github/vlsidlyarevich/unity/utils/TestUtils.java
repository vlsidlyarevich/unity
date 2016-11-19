package com.github.vlsidlyarevich.unity.utils;

import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;


public final class TestUtils {

    private static final int MAX_STRING_LENGTH = 15;
    private static final int START_INCLUSIVE = 5000;
    private static final int END_INCLUSIVE = 100000;

    private TestUtils() {

    }

    private static String getRandomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    private static Integer getRandomInt(int startInclusive, int endInclusive) {
        return RandomUtils.nextInt(startInclusive, endInclusive);
    }

    public static WorkerProfile generateWorkerProfile() {
        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setName(new Name(getRandomString(MAX_STRING_LENGTH), getRandomString(MAX_STRING_LENGTH)));
        workerProfile.setSpeciality(Speciality.SOFTWARE_ENGINEER);
        workerProfile.setAge(getRandomInt(18, 50));
        workerProfile.setSkype(getRandomString(MAX_STRING_LENGTH));
        workerProfile.setGender(Gender.MALE);
        workerProfile.setPhone("8 800 555 35 35");

        return workerProfile;
    }

    public static WorkerProfileDTO generateWorkerProfileDTO() {
        WorkerProfileDTO workerProfile = new WorkerProfileDTO();
        workerProfile.setName(new Name(getRandomString(MAX_STRING_LENGTH), getRandomString(MAX_STRING_LENGTH)));
        workerProfile.setSpeciality(Speciality.SOFTWARE_ENGINEER);
        workerProfile.setAge(getRandomInt(18, 50));
        workerProfile.setSkype(getRandomString(MAX_STRING_LENGTH));
        workerProfile.setGender(Gender.MALE);
        workerProfile.setPhone("8 800 555 35 35");

        return workerProfile;
    }

    public static Vacancy generateVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setSpeciality(Speciality.SOFTWARE_ENGINEER);
        vacancy.setSalary(getRandomInt(0, 2000).toString());
        vacancy.setJobType(JobType.CONTRACT);
        vacancy.setDescription(getRandomString(20));
        vacancy.setLocation(getRandomString(10));

        return vacancy;
    }

}
