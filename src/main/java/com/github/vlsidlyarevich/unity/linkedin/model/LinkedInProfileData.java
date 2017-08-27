package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.social.linkedin.api.CurrentShare;
import org.springframework.social.linkedin.api.Education;
import org.springframework.social.linkedin.api.ImAccount;
import org.springframework.social.linkedin.api.LinkedInDate;
import org.springframework.social.linkedin.api.Location;
import org.springframework.social.linkedin.api.PhoneNumber;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;
import org.springframework.social.linkedin.api.Relation;
import org.springframework.social.linkedin.api.TwitterAccount;
import org.springframework.social.linkedin.api.UrlResource;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfileData extends LinkedInResult {

    private static final long serialVersionUID = -1433270981256414337L;

    private List<Position> positions;
    private List<Position> threeCurrentPositions;
    private List<Position> threePastPositions;
    private List<Recommendation> recommendationsReceived;
    private List<ImAccount> imAccounts;
    private List<TwitterAccount> twitterAccounts;
    private List<UrlResource> memberUrlResources;
    private List<PhoneNumber> phoneNumbers;
    private List<String> skills;
    private List<Education> educations;
    private String proposalComments;
    private String specialties;
    private int numConnections;
    private boolean numConnectionsCapped;
    private int numRecommenders;
    private String mainAddress;
    private String associations;
    private Location location;
    private String interests;
    private String honors;
    private int distance;
    private LinkedInDate dateOfBirth;
    private CurrentShare currentShare;
    private Relation relationToViewer;
}
