package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class LoginDTO implements Serializable {

    private String userName;
    private String password;

    public LoginDTO() {

    }
}
