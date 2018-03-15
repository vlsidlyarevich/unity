package com.github.vlsidlyarevich.unity.linkedin.model;

import com.github.vlsidlyarevich.unity.common.model.LinkedInResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkedInCompany extends LinkedInResult {

    private static final long serialVersionUID = -3473404073294819440L;

    private int id;
    private String name;
    private String industry;
    private String size;
    private String type;
    private String blogRssUrl;
    private LinkedInCodeAndName companyType;
    private String description;
    private List<String> emailDomains;
    private LinkedInCodeAndName employeeCountRange;
    private int foundedYear;
    private String logoUrl;
    private int numFollowers;
    private List<String> specialties;
    private String squareLogoUrl;
    private LinkedInCodeAndName status;
    private LinkedInCodeAndName stockExchange;
    private String ticker;
    private String twitterId;
    private String universalName;
    private String websiteUrl;
}
