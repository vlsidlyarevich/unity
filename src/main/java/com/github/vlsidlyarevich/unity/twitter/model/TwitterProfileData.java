package com.github.vlsidlyarevich.unity.twitter.model;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TwitterProfileData extends TwitterResult {

    private static final long serialVersionUID = 3189181698730273714L;

    private long id;
    private String name;
    private String email;
    private String screenName;
    private String location;
    private String description;
    private String profileImageURL;
    private String url;
    private int followersCount;
    private Date createdAt;
    private String lang;
    private boolean isVerified;
    private List<TwitterSubscriptionData> subscriptions;
}
