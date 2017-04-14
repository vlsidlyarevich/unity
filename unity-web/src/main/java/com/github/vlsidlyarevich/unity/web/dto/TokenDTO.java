package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDTO implements Serializable {
    private static final long serialVersionUID = -474974632395562614L;

    private String token;
}
