package com.github.vlsidlyarevich.unity.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TokenDTO implements Serializable {
    private static final long serialVersionUID = -474974632395562614L;

    private String token;
}
