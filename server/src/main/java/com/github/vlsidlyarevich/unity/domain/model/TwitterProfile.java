package com.github.vlsidlyarevich.unity.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "popular_twitter_profiles")
public class TwitterProfile extends DbModel implements Serializable {

    private static final long serialVersionUID = -7882544880017543727L;

    @Id
    private String id;
    private String name;
    private List<String> tags;
    private String url;
}
