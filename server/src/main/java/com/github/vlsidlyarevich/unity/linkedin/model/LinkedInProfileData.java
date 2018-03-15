package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfileData extends LinkedInResult {

    private static final long serialVersionUID = -1433270981256414337L;

    private List<LinkedInProfilePosition> positions;
    private List<LinkedInProfilePosition> threeCurrentPositions;
    private List<LinkedInProfilePosition> threePastPositions;
    private List<LinkedInProfileRecommendation> recommendationsReceived;
    private List<LinkedInProfileUrlResource> memberUrlResources;
    private List<LinkedInProfilePhoneNumber> phoneNumbers;
    private List<String> skills;
    private List<LinkedInProfileEducation> educations;
    private String proposalComments;
    private String specialties;
    private int numConnections;
    private boolean numConnectionsCapped;
    private int numRecommenders;
    private String mainAddress;
    private String associations;
    private String interests;
    private String honors;
    private int distance;
    private LinkedInDate dateOfBirth;
}
