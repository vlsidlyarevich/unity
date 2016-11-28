package com.github.vlsidlyarevich.unity.utils;

import com.github.vlsidlyarevich.unity.dto.CandidateDTO;
import com.github.vlsidlyarevich.unity.dto.VacancyDTO;
import com.github.vlsidlyarevich.unity.dto.WorkerProfileDTO;
import com.github.vlsidlyarevich.unity.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;


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

    public static VacancyDTO generateVacancyDTO() {
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setSpeciality(Speciality.SOFTWARE_ENGINEER);
        vacancyDTO.setSalary(getRandomInt(0, 2000).toString());
        vacancyDTO.setJobType(JobType.CONTRACT);
        vacancyDTO.setDescription(getRandomString(20));
        vacancyDTO.setLocation(getRandomString(10));

        return vacancyDTO;
    }

    public static Candidate generateCandidate() {
        Candidate candidate = new Candidate();
        candidate.setGender(Gender.MALE);
        candidate.setName(new Name(getRandomString(MAX_STRING_LENGTH), getRandomString(MAX_STRING_LENGTH)));
        candidate.setGithubUrl(getRandomString(MAX_STRING_LENGTH) + "@git");
        candidate.setHrSkype(getRandomString(MAX_STRING_LENGTH));
        candidate.setLinkedInUrl("https://www.linkedin.com/in" + getRandomString(MAX_STRING_LENGTH));
        candidate.setImageId(getRandomString(MAX_STRING_LENGTH));

        return candidate;
    }

    public static CandidateDTO generateCandidateDTO() {
        CandidateDTO dto = new CandidateDTO();
        dto.setGender(Gender.MALE);
        dto.setName(new Name(getRandomString(MAX_STRING_LENGTH), getRandomString(MAX_STRING_LENGTH)));
        dto.setGithubUrl(getRandomString(MAX_STRING_LENGTH) + "@git");
        dto.setHrSkype(getRandomString(MAX_STRING_LENGTH));
        dto.setLinkedInUrl("https://www.linkedin.com/in" + getRandomString(MAX_STRING_LENGTH));
        dto.setImageId(getRandomString(MAX_STRING_LENGTH));

        return dto;
    }
}
