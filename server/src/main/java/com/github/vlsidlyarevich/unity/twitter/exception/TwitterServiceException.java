package com.github.vlsidlyarevich.unity.twitter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import twitter4j.TwitterException;

@Data
@EqualsAndHashCode(callSuper = true)
public class TwitterServiceException extends RuntimeException {

    private static final long serialVersionUID = -7558504483558391000L;

    private final TwitterException exception;
}
