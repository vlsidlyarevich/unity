package com.github.vlsidlyarevich.unity.twitter.model;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import twitter4j.User;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TwitterProfileData extends TwitterResult {

    private static final long serialVersionUID = 3189181698730273714L;

    private List<User> subscriptions;
}
