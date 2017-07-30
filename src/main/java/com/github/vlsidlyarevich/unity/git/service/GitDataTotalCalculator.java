package com.github.vlsidlyarevich.unity.git.service;

import com.github.vlsidlyarevich.unity.common.model.GitResult;

import java.util.Collection;
import java.util.Map;

public interface GitDataTotalCalculator {

    Map<String, Integer> calculateTotal(Collection<? extends GitResult> gitResultCollection);
}
