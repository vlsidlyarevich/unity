package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;


@Data
public class UserDTO {

    private String username;
    private String password;

    public UserDTO() {

    }
}
