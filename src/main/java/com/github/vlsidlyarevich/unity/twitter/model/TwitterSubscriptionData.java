package com.github.vlsidlyarevich.unity.twitter.model;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class TwitterSubscriptionData extends TwitterResult {

    private static final long serialVersionUID = -1073551767038352922L;
    private String id;
    private String name;
    private String email;
    private String screenName;
    private String location;
    private String description;
    //FIXME
    private String url;
    private int followersCount;
    private boolean showAllInlineMedia;
    private int friendsCount;
    private Date createdAt;
    private String lang;
    private boolean verified;
    private String profileImageURL;
    private String miniProfileImageURL;
    private String originalProfileImageURL;
}


