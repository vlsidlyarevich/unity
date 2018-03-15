package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.social.linkedin.api.Recommendation;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInProfileRecommendation extends LinkedInResult {

    private static long serialVersionUID = 4323603993889138909L;

    private String id;
    private String recommendationSnippet;
    private String recommendationText;
    private Recommendation.RecommendationType recommendationType;
    private LinkedInProfile recommender;
    private LinkedInProfile recommendee;
}
