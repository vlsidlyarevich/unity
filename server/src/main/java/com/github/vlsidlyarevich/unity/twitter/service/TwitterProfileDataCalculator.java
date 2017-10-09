package com.github.vlsidlyarevich.unity.twitter.service;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;

import java.util.Collection;
import java.util.Map;

public interface TwitterProfileDataCalculator {

    Map<String, Integer> calculateTotal(Collection<? extends TwitterResult> gitResultCollection);
}
