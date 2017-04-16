package com.github.vlsidlyarevich.unity.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "usersDetails")
public class UserSocial extends DbModel implements Serializable {

    private static final long serialVersionUID = 3134293756973082637L;

    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String skype;
    private String additional;
    private String image;
}
