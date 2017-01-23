package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

    private String userName;
    private String password;

    public UserDTO() {

    }
}
