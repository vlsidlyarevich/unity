package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;

@Data
public class UserProfile extends BaseEntity {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;

}
