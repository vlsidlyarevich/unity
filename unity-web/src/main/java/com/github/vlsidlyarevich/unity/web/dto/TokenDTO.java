package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class TokenDTO implements Serializable {

    private String token;

    public TokenDTO() {
    }
}
