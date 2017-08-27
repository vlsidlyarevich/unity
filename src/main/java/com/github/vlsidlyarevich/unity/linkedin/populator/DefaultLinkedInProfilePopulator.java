package com.github.vlsidlyarevich.unity.linkedin.populator;

import com.github.vlsidlyarevich.unity.linkedin.model.LinkedInProfileData;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Component;

@Component
public class DefaultLinkedInProfilePopulator implements LinkedInProfilePopulator {

    @Override
    public LinkedInProfileData populate(final LinkedInProfileFull model) {
        final LinkedInProfileData profileData = new LinkedInProfileData();

        profileData.setPositions(model.getPositions());
        profileData.setThreeCurrentPositions(model.getThreeCurrentPositions());
        profileData.setThreePastPositions(model.getThreePastPositions());
        profileData.setRecommendationsReceived(model.getRecommendationsReceived());
        profileData.setImAccounts(model.getImAccounts());
        profileData.setTwitterAccounts(model.getTwitterAccounts());
        profileData.setMemberUrlResources(model.getMemberUrlResources());
        profileData.setPhoneNumbers(model.getPhoneNumbers());
        profileData.setSkills(model.getSkills());
        profileData.setEducations(model.getEducations());
        profileData.setProposalComments(model.getProposalComments());
        profileData.setSpecialties(model.getSpecialties());
        profileData.setNumConnections(model.getNumConnections());
        profileData.setNumConnectionsCapped(model.isNumConnectionsCapped());
        profileData.setNumRecommenders(model.getNumRecommenders());
        profileData.setMainAddress(model.getMainAddress());
        profileData.setAssociations(model.getAssociations());
        profileData.setLocation(model.getLocation());
        profileData.setInterests(model.getInterests());
        profileData.setHonors(model.getHonors());
        profileData.setDistance(model.getDistance());
        profileData.setDateOfBirth(model.getDateOfBirth());
        profileData.setCurrentShare(model.getCurrentShare());
        profileData.setRelationToViewer(model.getRelationToViewer());

        return profileData;
    }

    @Override
    public boolean canPopulate(final Object model) {
        return model instanceof LinkedInProfileFull;
    }
}
