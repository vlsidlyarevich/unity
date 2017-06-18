package com.github.vlsidlyarevich.unity.git.populator;


public interface GitPopulator<D, M> {

    D populate(M model);

    boolean canPopulate(Object model);
}
