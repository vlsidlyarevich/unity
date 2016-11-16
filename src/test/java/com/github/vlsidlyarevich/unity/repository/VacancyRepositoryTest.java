package com.github.vlsidlyarevich.unity.repository;

import com.github.vlsidlyarevich.unity.Application;
import com.github.vlsidlyarevich.unity.model.Candidate;
import com.github.vlsidlyarevich.unity.model.JobType;
import com.github.vlsidlyarevich.unity.model.Speciality;
import com.github.vlsidlyarevich.unity.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;


@SpringApplicationConfiguration(Application.class)
public class VacancyRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void saveTest() throws Exception {
        Vacancy vacancy = new Vacancy();
        vacancy.setDatePosted(new Date());
        vacancy.setDescription("Description");
        vacancy.setJobType(JobType.CONTRACT);
        vacancy.setSalary("1000$ p/m");
        vacancy.setSpeciality(Speciality.SYSTEM_ADMINISTRATOR);
        Candidate candidate = new Candidate();
        candidate.setGithubUrl("git");
        vacancy.addCandidate(candidate);

        vacancyRepository.save(vacancy);
    }

}
