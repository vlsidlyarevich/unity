package com.github.vlsidlyarevich.unity.linkedin.populator;

public interface LinkedInPopulator<D, M> {

    D populate(M model);

    boolean canPopulate(Object model);
}
