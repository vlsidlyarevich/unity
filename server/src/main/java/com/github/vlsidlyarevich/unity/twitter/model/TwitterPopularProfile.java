package com.github.vlsidlyarevich.unity.twitter.model;

import com.github.vlsidlyarevich.unity.common.model.TwitterResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TwitterPopularProfile extends TwitterResult {

    private static final long serialVersionUID = -6308210106663324416L;

    private String name;
    private List<String> tags;
    private String url;
}
