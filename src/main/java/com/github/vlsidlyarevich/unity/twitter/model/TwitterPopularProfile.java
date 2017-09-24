package com.github.vlsidlyarevich.unity.twitter.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TwitterPopularProfile implements Serializable {

    private static final long serialVersionUID = 5805754163699275250L;

    private String name;
    private List<String> tags;
    private String url;
}
