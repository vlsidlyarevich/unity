package com.github.vlsidlyarevich.unity.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public final class TokenDTO implements Serializable {
    private static final long serialVersionUID = -474974632395562614L;

    private String token;

    public TokenDTO(final String token) {
        this.token = token;
    }
}
